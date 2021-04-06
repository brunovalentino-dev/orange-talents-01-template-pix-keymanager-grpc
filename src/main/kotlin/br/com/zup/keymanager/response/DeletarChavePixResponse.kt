package br.com.zup.keymanager.response

import java.time.LocalDateTime

data class DeletarChavePixResponse (
    val key: String,
    val participant: String,
    val deletedAt: LocalDateTime
)