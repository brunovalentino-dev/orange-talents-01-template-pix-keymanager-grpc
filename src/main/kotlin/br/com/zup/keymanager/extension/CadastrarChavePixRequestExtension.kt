package br.com.zup.keymanager.extension

import br.com.zup.CadastrarChavePixRequest
import br.com.zup.TipoConta
import br.com.zup.TipoChavePix
import br.com.zup.keymanager.request.ChavePixRequest

fun CadastrarChavePixRequest.toEntity() : ChavePixRequest {
    return ChavePixRequest(
        clienteId = idCliente,
        tipoChavePix = when(tipoChavePix) {
            TipoChavePix.CHAVE_DESCONHECIDA -> null
            else -> TipoChavePix.valueOf(tipoChavePix.name)
        },
        chavePix = chavePix,
        tipoConta = when(tipoConta) {
            TipoConta.CONTA_DESCONHECIDA -> null
            else -> TipoConta.valueOf(tipoConta.name)
        }
    )
}