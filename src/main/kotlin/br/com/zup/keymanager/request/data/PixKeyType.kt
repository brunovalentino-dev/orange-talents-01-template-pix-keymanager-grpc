package br.com.zup.keymanager.request.data

import br.com.zup.TipoChavePix

enum class PixKeyType (
    val tipoChavePix: TipoChavePix
)
{
    CPF(TipoChavePix.CPF),
    NUMERO_CELULAR(TipoChavePix.NUMERO_CELULAR),
    EMAIL(TipoChavePix.EMAIL),
    ALEATORIA(TipoChavePix.CHAVE_ALEATORIA);

    companion object {
        private val pixKeyMapping = PixKeyType.values().associateBy { it.tipoChavePix }

        fun by(tipoChavePix: TipoChavePix) : PixKeyType {
            return pixKeyMapping[tipoChavePix] ?:
                throw IllegalArgumentException("Tipo de chave PIX invalida!")
        }
    }
}