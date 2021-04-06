package br.com.zup.keymanager.response

import br.com.zup.keymanager.model.Conta

data class DadosContaResponse(
    val tipo: String,
    val instituicao: InstituicaoResponse,
    val agencia: String,
    val numero: String,
    val titular: TitularResponse
)
{
    fun toEntity(): Conta {
        return Conta(
            instituicao = this.instituicao.nome,
            agencia = this.agencia,
            numeroConta = this.numero,
            nomeTitular = this.titular.nome,
            cpfTitular = this.titular.cpf
        )
    }
}
