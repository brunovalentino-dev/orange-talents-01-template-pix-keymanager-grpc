package br.com.zup.keymanager.request

import br.com.zup.keymanager.model.ChavePix
import br.com.zup.keymanager.model.Conta
import br.com.zup.keymanager.request.data.BankAccount
import br.com.zup.keymanager.request.data.Owner
import br.com.zup.keymanager.request.data.PixKeyType

data class RegistrarChavePixRequest (
    val keyType: PixKeyType,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner
)
{
    companion object {
        fun of(chavePix: ChavePix) : RegistrarChavePixRequest {
            return RegistrarChavePixRequest (
                keyType = PixKeyType.by(chavePix.tipoChavePix),
                key = chavePix.chavePix,
                bankAccount = BankAccount (
                    participant = Conta.ITAU_ISPB,
                    branch = chavePix.conta.agencia,
                    accountNumber = chavePix.conta.numeroConta,
                    accountType = BankAccount.AccountType.by(chavePix.tipoConta)
                ),
                owner = Owner (
                    type = Owner.OwnerType.NATURAL_PERSON,
                    name = chavePix.conta.nomeTitular,
                    taxIdNumber = chavePix.conta.cpfTitular
                )
            )
        }
    }
}