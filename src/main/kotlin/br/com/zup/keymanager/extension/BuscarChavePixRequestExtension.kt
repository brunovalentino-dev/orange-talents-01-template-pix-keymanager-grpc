package br.com.zup.keymanager.extension

import br.com.zup.BuscarChavePixRequest
import br.com.zup.BuscarChavePixRequest.FiltroCase.*

import javax.validation.ConstraintViolationException
import javax.validation.Validator

fun BuscarChavePixRequest.toEntity(validator: Validator) : Filtro {
    val filtro = when(filtroCase) {
        FILTROID -> filtroId.let {
            Filtro.PorIdChavePix(it.idCliente, it.idChavePix)
        }
        CHAVE -> Filtro.PorChave(chave)
        FILTRO_NOT_SET -> Filtro.Invalido()
    }

    val invalidEntries = validator.validate(filtro)

    if (invalidEntries.isNotEmpty()) {
        throw ConstraintViolationException(invalidEntries)
    }

    return filtro
}