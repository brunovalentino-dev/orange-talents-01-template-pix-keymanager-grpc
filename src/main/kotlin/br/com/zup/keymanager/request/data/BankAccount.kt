package br.com.zup.keymanager.request.data

import br.com.zup.TipoConta

data class BankAccount(
    val participant: String,
    val branch: String,
    val accountNumber: String,
    val accountType: AccountType?,
)
{
    enum class AccountType {
        CACC,
        SVGS;

        companion object {
            fun by(tipoConta: TipoConta): AccountType? {
                return when (tipoConta) {
                    TipoConta.CONTA_CORRENTE -> CACC
                    TipoConta.CONTA_POUPANCA -> SVGS
                    else -> null
                }
            }
        }
    }
}
