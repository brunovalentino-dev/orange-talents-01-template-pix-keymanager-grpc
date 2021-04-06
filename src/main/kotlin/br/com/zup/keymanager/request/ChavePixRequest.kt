package br.com.zup.keymanager.request

import br.com.zup.TipoConta
import br.com.zup.keymanager.model.ChavePix
import br.com.zup.keymanager.model.Conta
import br.com.zup.keymanager.validation.ClienteValido
import br.com.zup.TipoChavePix
import br.com.zup.keymanager.validation.ChavePixValida
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ChavePixValida
@Introspected
data class ChavePixRequest(
    @field:ClienteValido //TODO: Validar entrada de dados UUID (genérica)
    @field:NotBlank
    val clienteId: String?,
    @field:NotNull
    val tipoChavePix: TipoChavePix?,
    @field:Size(max = 77)
    val chavePix: String?,
    @field:NotNull
    val tipoConta: TipoConta?
)
{
    fun toEntity(conta: Conta): ChavePix {
        return ChavePix(
            clienteId = UUID.fromString(this.clienteId),
            tipoChavePix = TipoChavePix.valueOf(this.tipoChavePix!!.name), //TODO: Revisar uso do non-null asserted
            chavePix = when(this.tipoChavePix) {
                TipoChavePix.CHAVE_ALEATORIA -> UUID.randomUUID().toString()
                else -> this.chavePix!!
            },
            tipoConta = TipoConta.valueOf(this.tipoConta!!.name), //TODO: Revisar uso do non-null asserted
            conta = conta
        )
    }
}
