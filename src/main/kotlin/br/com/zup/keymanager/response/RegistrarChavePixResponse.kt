package br.com.zup.keymanager.response

import br.com.zup.keymanager.request.data.BankAccount
import br.com.zup.keymanager.request.data.Owner
import br.com.zup.keymanager.request.data.PixKeyType
import java.time.LocalDateTime

data class RegistrarChavePixResponse (
    val keyType: PixKeyType,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner,
    val createdAt: LocalDateTime
)