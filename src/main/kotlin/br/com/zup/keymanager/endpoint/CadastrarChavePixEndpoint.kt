package br.com.zup.keymanager.endpoint

import br.com.zup.CadastrarChavePixRequest
import br.com.zup.CadastrarChavePixResponse
import br.com.zup.CadastrarChavePixServiceGrpc
import br.com.zup.keymanager.exception.ChavePixNaoRegistradaException
import br.com.zup.keymanager.exception.ChavePixRegistradaException
import br.com.zup.keymanager.extension.toEntity
import br.com.zup.keymanager.service.CadastrarChavePixService
import io.grpc.Status
import io.grpc.stub.StreamObserver
import io.micronaut.http.client.exceptions.HttpClientResponseException
import javax.inject.Inject
import javax.inject.Singleton
import javax.validation.ConstraintViolationException

@Singleton
class CadastrarChavePixEndpoint : CadastrarChavePixServiceGrpc.CadastrarChavePixServiceImplBase() {

    @Inject
    lateinit var cadastrarChavePixService: CadastrarChavePixService

    override fun cadastrar(request: CadastrarChavePixRequest,
                           responseObserver: StreamObserver<CadastrarChavePixResponse>) {
        val chavePixRequest = request.toEntity()

        try {
            val chavePix = cadastrarChavePixService.cadastrar(chavePixRequest)

            responseObserver.onNext(CadastrarChavePixResponse.newBuilder()
                .setIdCliente(chavePix.clienteId.toString())
                .setIdChavePix(chavePix.id.toString())
                .build())
            responseObserver.onCompleted()
        }
        catch (e: ChavePixRegistradaException) {
            val error = Status.ALREADY_EXISTS
                .withDescription(e.message)
                .asRuntimeException()
            responseObserver.onError(error)

            return
        }
        catch (e: ChavePixNaoRegistradaException) {
            val error = Status.NOT_FOUND
                .withDescription("Cliente não identificado!")
                .asRuntimeException()
            responseObserver.onError(error)

            return
        }
        catch (e: IllegalStateException) {
            val error = Status.INTERNAL
                .withDescription(e.message)
                .asRuntimeException()
            responseObserver.onError(error)

            return
        }
        catch (e: ConstraintViolationException) {
            val error = Status.INVALID_ARGUMENT
                .withDescription(e.message)
                .asRuntimeException()
            responseObserver.onError(error)

            return
        }
    }

}
