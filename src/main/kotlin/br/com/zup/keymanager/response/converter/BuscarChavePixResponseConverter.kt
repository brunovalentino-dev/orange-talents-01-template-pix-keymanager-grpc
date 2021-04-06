package br.com.zup.keymanager.response.converter

import br.com.zup.BuscarChavePixResponse
import br.com.zup.TipoChavePix
import br.com.zup.TipoConta
import br.com.zup.keymanager.extension.ChavePixData
import com.google.protobuf.Timestamp
import java.time.ZoneId

class BuscarChavePixResponseConverter {

    fun converter(chavePixData: ChavePixData) : BuscarChavePixResponse {
        return BuscarChavePixResponse.newBuilder()
            .setIdCliente(chavePixData.idCliente?.toString() ?: "")
            .setIdChavePix(chavePixData.idChavePix?.toString() ?: "")
            .setChavePix(
                BuscarChavePixResponse.ChavePix.newBuilder()
                    .setTipoChavePix(TipoChavePix.valueOf(chavePixData.tipoChavePix.name))
                    .setChavePix(chavePixData.chavePix)
                    .setConta(BuscarChavePixResponse.ChavePix.Conta.newBuilder()
                        .setTipoConta(TipoConta.valueOf(chavePixData.tipoConta.name))
                        .setInstituicao(chavePixData.conta.instituicao)
                        .setNomeTitular(chavePixData.conta.nomeTitular)
                        .setCpfTitular(chavePixData.conta.cpfTitular)
                        .setAgencia(chavePixData.conta.agencia)
                        .setNumeroConta(chavePixData.conta.numeroConta)
                        .build()
                    )
                    .setCriadaEm(chavePixData.criadaEm.let {
                        val timestamp = it.atZone(ZoneId.of("UTC")).toInstant()
                        Timestamp.newBuilder()
                            .setSeconds(timestamp.epochSecond)
                            .setNanos(timestamp.nano)
                            .build()
                    })
                    .build()
            )
            .build()
    }

}