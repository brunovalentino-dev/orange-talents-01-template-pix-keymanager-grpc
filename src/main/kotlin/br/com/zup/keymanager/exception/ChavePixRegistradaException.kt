package br.com.zup.keymanager.exception

class ChavePixRegistradaException(
    mensagem: String = "A chave PIX informada já foi cadastrada!"
) : Exception(mensagem)
