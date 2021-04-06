package br.com.zup.keymanager.validation

import br.com.zup.keymanager.extension.validar
import br.com.zup.keymanager.request.ChavePixRequest
import javax.inject.Singleton
import javax.validation.ConstraintValidator // TODO: Verificar o import do Micronaut vs. Hibernate...
import javax.validation.ConstraintValidatorContext

@Singleton
class ChavePixValidator : ConstraintValidator<ChavePixValida, ChavePixRequest> {

    override fun isValid(value: ChavePixRequest?, context: ConstraintValidatorContext?): Boolean {
        if (value?.tipoChavePix == null) {
            return false
        }

        return value.validar(value.chavePix)
    }

}