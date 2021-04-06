package br.com.zup.keymanager.service

import br.com.zup.keymanager.client.PixBCBClient
import br.com.zup.keymanager.exception.ChavePixNaoRegistradaException
import br.com.zup.keymanager.repository.ChavePixRepository
import br.com.zup.keymanager.request.DeletarChavePixRequest
import io.micronaut.http.HttpStatus
import io.micronaut.validation.Validated
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import kotlin.IllegalStateException

@Validated
@Singleton
class RemoverChavePixService {

    @Inject
    lateinit var pixBCBClient: PixBCBClient

    @Inject
    lateinit var chavePixRepository: ChavePixRepository

    @Transactional
    fun remover(idChavePix: String, idCliente: String): Unit {
        val idChavePixUUID = UUID.fromString(idChavePix)
        val idClienteUUID = UUID.fromString(idCliente)

        val chavePixEncontrada =
            chavePixRepository.findByIdAndClienteId(idChavePixUUID, idClienteUUID)
                .orElseThrow { ChavePixNaoRegistradaException() }

        chavePixRepository.deleteById(idChavePixUUID)

        val deleteRequest = DeletarChavePixRequest(chavePixEncontrada.chavePix)
        val deleteResponse = pixBCBClient.deletarChavePix(chavePixEncontrada.chavePix,
            deleteRequest)

        if (deleteResponse.status != HttpStatus.OK) {
            throw IllegalStateException("Erro ao excluir chave PIX no Banco Central.")
        }
    }

}