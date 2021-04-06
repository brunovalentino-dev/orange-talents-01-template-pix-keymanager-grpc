package br.com.zup.keymanager.endpoint

import br.com.zup.ListarChavePixRequest
import br.com.zup.ListarChavePixResponse
import br.com.zup.ListarChavePixServiceGrpc
import br.com.zup.keymanager.repository.ChavePixRepository
import com.google.protobuf.Timestamp
import io.grpc.stub.StreamObserver
import java.time.ZoneId
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListarChavePixEndpoint : ListarChavePixServiceGrpc.ListarChavePixServiceImplBase() {

    @Inject
    lateinit var chavePixRepository: ChavePixRepository

    override fun listar(request: ListarChavePixRequest,
                        responseObserver: StreamObserver<ListarChavePixResponse>) {
        if (request.idCliente.isNullOrBlank()) {
            throw IllegalArgumentException("O identificador do cliente não deve ser nulo/vazio!")
        }

        val idCliente = UUID.fromString(request.idCliente)
        val listaChavePix = chavePixRepository.findAllByClienteId(idCliente)
            .map { it -> ListarChavePixResponse.ChavePix.newBuilder()
                    .setIdChavePix(it.id.toString())
                    .setTipoChavePix(it.tipoChavePix)
                    .setChavePix(it.chavePix)
                    .setTipoConta(it.tipoConta)
                    .setCriadaEm(it.criadoEm.let {
                        val timestamp = it.atZone(ZoneId.of("UTC")).toInstant()
                        Timestamp.newBuilder()
                            .setSeconds(timestamp.epochSecond)
                            .setNanos(timestamp.nano)
                            .build()
                    })
                    .build()
            }

        responseObserver.onNext(ListarChavePixResponse.newBuilder()
            .setIdCliente(idCliente.toString())
            .addAllListaChavePix(listaChavePix)
            .build())
        responseObserver.onCompleted()
    }

}