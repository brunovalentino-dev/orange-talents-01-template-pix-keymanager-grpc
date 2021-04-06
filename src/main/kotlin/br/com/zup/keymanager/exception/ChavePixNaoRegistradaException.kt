package br.com.zup.keymanager.exception

class ChavePixNaoRegistradaException(
    message: String = "A chave PIX informada não foi encontrada!"
) : Exception(message)
