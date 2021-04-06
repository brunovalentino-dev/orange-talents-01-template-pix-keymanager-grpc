package br.com.zup.keymanager.extension

import br.com.zup.TipoChavePix
import br.com.zup.keymanager.request.ChavePixRequest
import io.micronaut.validation.validator.constraints.EmailValidator

fun ChavePixRequest.validar(chavePix: String?): Boolean {
    when(tipoChavePix) {
        TipoChavePix.CPF -> {
            if (chavePix.isNullOrBlank()) {
                return false
            }
            else if (!chavePix.matches("^[0-9]{11}$".toRegex())) {
                return false
            }

            return true
        }
        TipoChavePix.NUMERO_CELULAR -> {
            if (chavePix.isNullOrBlank()) {
                return false
            }
            else if (!chavePix.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex())) {
                return false
            }

            return true
        }
        TipoChavePix.EMAIL -> {
            if (chavePix.isNullOrBlank()) {
                return false
            }
            return EmailValidator().run {
                initialize(null)
                isValid(chavePix, null)
            }
        }
        TipoChavePix.CHAVE_ALEATORIA -> {
            if (chavePix.isNullOrBlank()) {
                return true
            }

            return false
        }
        else -> return false
    }
}