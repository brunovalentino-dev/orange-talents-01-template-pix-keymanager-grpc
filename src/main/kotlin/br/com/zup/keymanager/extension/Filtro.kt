package br.com.zup.keymanager.extension

import br.com.zup.keymanager.client.PixBCBClient
import br.com.zup.keymanager.exception.ChavePixNaoRegistradaException
import br.com.zup.keymanager.repository.ChavePixRepository
import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpStatus
import java.lang.IllegalArgumentException
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
sealed class Filtro {

    abstract fun filtrar(chavePixRepository: ChavePixRepository,
        pixBCBClient: PixBCBClient) : ChavePixData

    @Introspected
    data class PorIdChavePix (
        @field:NotBlank
        val idCliente: String,
        @field:NotBlank
        val idChavePix: String
    ) : Filtro()
    {
        fun idClienteParaUUID() = UUID.fromString(idCliente)
        fun idChavePixParaUUID() = UUID.fromString(idChavePix)

        override fun filtrar(chavePixRepository: ChavePixRepository, pixBCBClient: PixBCBClient): ChavePixData {
            return chavePixRepository.findByIdAndClienteId(idChavePixParaUUID(), idClienteParaUUID())
                .map { ChavePixData.of(it) }
                .orElseThrow{ ChavePixNaoRegistradaException("") }
        }
    }

    @Introspected
    data class PorChave (
        @field:NotBlank
        @field:Size(max = 77)
        val chavePix: String
    ) : Filtro()
    {
        override fun filtrar(chavePixRepository: ChavePixRepository, pixBCBClient: PixBCBClient): ChavePixData {
            return chavePixRepository.findByChavePix(chavePix)
                .map { ChavePixData.of(it) }
                .orElseGet {
                    val response = pixBCBClient.buscarChavePix(chavePix)

                    when (response.status) {
                        HttpStatus.OK -> response?.body().toData()
                        else -> throw ChavePixNaoRegistradaException()
                    }
                }
        }
    }

    @Introspected
    class Invalido : Filtro() {
        override fun filtrar(chavePixRepository: ChavePixRepository, pixBCBClient: PixBCBClient): ChavePixData {
            throw IllegalArgumentException("Chave PIX inválida!")
        }
    }
}
