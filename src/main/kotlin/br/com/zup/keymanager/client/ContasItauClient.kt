package br.com.zup.keymanager.client

import br.com.zup.keymanager.response.DadosContaResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("\${contas.itau.url}")
interface ContasItauClient {

    @Get("/api/v1/clientes/{clienteId}/contas{?tipo}")
    fun consultarContas(@PathVariable clienteId: String, @QueryValue tipo: String) :
            HttpResponse<DadosContaResponse>
}