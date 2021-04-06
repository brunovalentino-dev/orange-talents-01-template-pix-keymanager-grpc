package br.com.zup.keymanager.endpoint

import br.com.zup.RemoverChavePixRequest
import br.com.zup.RemoverChavePixServiceGrpc
import br.com.zup.keymanager.exception.ChavePixNaoRegistradaException
import br.com.zup.keymanager.service.RemoverChavePixService
import com.google.protobuf.Empty
import io.grpc.Status
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoverChavePixEndpoint : RemoverChavePixServiceGrpc.RemoverChavePixServiceImplBase() {

    @Inject
    lateinit var removerChavePixService: RemoverChavePixService

    override fun remover(request: RemoverChavePixRequest, responseObserver: StreamObserver<Empty>) {
        try {
            removerChavePixService.remover(request.idChavePix, request.idCliente)

            responseObserver.onNext(Empty.getDefaultInstance())
            responseObserver.onCompleted()
        }
        catch (e: ChavePixNaoRegistradaException) {
            val error = Status.NOT_FOUND
                .withDescription(e.message)
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
    }

}