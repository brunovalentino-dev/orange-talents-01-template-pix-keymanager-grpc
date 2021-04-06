package br.com.zup.keymanager.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.TYPE
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ChavePixValidator::class])
@Retention(RUNTIME)
@Target(CLASS, TYPE)
annotation class ChavePixValida(
    val message: String = "Chave PIX invalida!",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)
