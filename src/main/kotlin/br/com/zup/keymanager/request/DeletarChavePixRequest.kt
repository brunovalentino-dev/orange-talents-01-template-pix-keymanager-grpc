package br.com.zup.keymanager.request

import br.com.zup.keymanager.model.Conta

data class DeletarChavePixRequest (
    val key: String,
    val participant: String = Conta.ITAU_ISPB
)