package br.com.zup.keymanager.model

import javax.persistence.Embeddable
import javax.validation.constraints.NotBlank

@Embeddable
class Conta(
    @field:NotBlank
    val instituicao: String,

    @field:NotBlank
    val agencia: String,

    @field:NotBlank
    val numeroConta: String,

    @field:NotBlank
    val nomeTitular: String,

    @field:NotBlank
    val cpfTitular: String
)
{
    override fun toString(): String {
        return "Conta(instituicao='$instituicao', agencia='$agencia', numeroConta='$numeroConta'," +
                " nomeTitular='$nomeTitular', cpfTitular='$cpfTitular')"
    }

    companion object {
        const val ITAU_ISPB = "60701190"
    }
}
