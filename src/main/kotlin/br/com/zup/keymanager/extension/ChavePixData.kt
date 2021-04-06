package br.com.zup.keymanager.extension

import br.com.zup.TipoChavePix
import br.com.zup.TipoConta
import br.com.zup.keymanager.model.ChavePix
import br.com.zup.keymanager.model.Conta
import java.time.LocalDateTime
import java.util.*

data class ChavePixData (
    val idChavePix: UUID? = null,
    val idCliente: UUID? = null,
    val tipoChavePix: TipoChavePix,
    val chavePix: String,
    val tipoConta: TipoConta,
    val conta: Conta,
    val criadaEm: LocalDateTime = LocalDateTime.now()
)
{
    companion object {
        fun of(chavePix: ChavePix) : ChavePixData {
            return ChavePixData(
                idChavePix = chavePix.id,
                idCliente = chavePix.clienteId,
                tipoChavePix = chavePix.tipoChavePix,
                chavePix = chavePix.chavePix,
                tipoConta = chavePix.tipoConta,
                conta = chavePix.conta,
                criadaEm = chavePix.criadoEm
            )
        }
    }
}
