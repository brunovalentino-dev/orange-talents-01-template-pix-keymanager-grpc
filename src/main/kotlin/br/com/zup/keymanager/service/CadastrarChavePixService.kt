package br.com.zup.keymanager.service

import br.com.zup.keymanager.client.ContasItauClient
import br.com.zup.keymanager.client.PixBCBClient
import br.com.zup.keymanager.exception.ChavePixRegistradaException
import br.com.zup.keymanager.model.ChavePix
import br.com.zup.keymanager.repository.ChavePixRepository
import br.com.zup.keymanager.request.ChavePixRequest
import br.com.zup.keymanager.request.RegistrarChavePixRequest
import io.micronaut.http.HttpStatus
import io.micronaut.validation.Validated
import java.lang.IllegalStateException
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class CadastrarChavePixService {

    @Inject
    lateinit var contasItauClient: ContasItauClient

    @Inject
    lateinit var pixBCBClient: PixBCBClient

    @Inject
    lateinit var chavePixRepository: ChavePixRepository

    @Transactional
    fun cadastrar(@Valid chavePixRequest: ChavePixRequest): ChavePix {
        if (chavePixRepository.existsByChavePix(chavePixRequest.chavePix)) {
            throw ChavePixRegistradaException()
        }

        val response =
            contasItauClient.consultarContas(chavePixRequest.clienteId!!, chavePixRequest.tipoConta!!.name)
        val conta = response.body()?.toEntity()
        val chavePix = chavePixRequest.toEntity(conta!!)

        chavePixRepository.save(chavePix)

        val registerRequest = RegistrarChavePixRequest.of(chavePix)
        val registerResponse = pixBCBClient.registrarChavePix(registerRequest)

        if (registerResponse.status != HttpStatus.CREATED) {
            throw IllegalStateException("Erro ao registrar chave PIX no Banco Central.")
        }

        return chavePix
    }

}
