package br.com.zup.keymanager.response

import br.com.zup.TipoConta
import br.com.zup.keymanager.extension.ChavePixData
import br.com.zup.keymanager.model.Conta
import br.com.zup.keymanager.request.data.BankAccount
import br.com.zup.keymanager.request.data.Owner
import br.com.zup.keymanager.request.data.PixKeyType
import java.time.LocalDateTime

data class ChavePixDetalheResponse (
    val keyType: PixKeyType,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner,
    val createdAt: LocalDateTime
)
{
    fun toData() : ChavePixData {
        return ChavePixData(
            tipoChavePix = keyType.tipoChavePix,
            chavePix = this.key,
            tipoConta = when (this.bankAccount.accountType) {
                BankAccount.AccountType.CACC -> TipoConta.CONTA_CORRENTE
                BankAccount.AccountType.SVGS -> TipoConta.CONTA_POUPANCA
                else -> TipoConta.CONTA_DESCONHECIDA
            },
            conta = Conta(
                instituicao = Conta.ITAU_ISPB,
                nomeTitular = owner.name,
                cpfTitular = owner.taxIdNumber,
                agencia = bankAccount.branch,
                numeroConta = bankAccount.accountNumber
            )
        )
    }
}