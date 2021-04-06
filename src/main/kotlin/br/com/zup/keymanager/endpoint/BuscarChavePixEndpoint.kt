package br.com.zup.keymanager.endpoint

import br.com.zup.BuscarChavePixRequest
import br.com.zup.BuscarChavePixResponse
import br.com.zup.BuscarChavePixServiceGrpc
import br.com.zup.keymanager.client.PixBCBClient
import br.com.zup.keymanager.extension.toEntity
import br.com.zup.keymanager.repository.ChavePixRepository
import br.com.zup.keymanager.response.converter.BuscarChavePixResponseConverter
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton
import javax.validation.Validator


@Singleton
class BuscarChavePixEndpoint : BuscarChavePixServiceGrpc.BuscarChavePixServiceImplBase() {

    @Inject
    lateinit var validator: Validator

    @Inject
    lateinit var chavePixRepository: ChavePixRepository

    @Inject
    lateinit var pixBCBClient: PixBCBClient

    override fun buscar(request: BuscarChavePixRequest,
                        responseObserver: StreamObserver<BuscarChavePixResponse>) {
        val filtro = request.toEntity(validator)
        val chavePixData = filtro.filtrar(chavePixRepository, pixBCBClient)

        responseObserver.onNext(BuscarChavePixResponseConverter().converter(chavePixData))
        responseObserver.onCompleted()
    }

}