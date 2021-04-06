package br.com.zup.keymanager.client

import br.com.zup.BuscarChavePixResponse
import br.com.zup.keymanager.request.DeletarChavePixRequest
import br.com.zup.keymanager.request.RegistrarChavePixRequest
import br.com.zup.keymanager.response.ChavePixDetalheResponse
import br.com.zup.keymanager.response.DeletarChavePixResponse
import br.com.zup.keymanager.response.RegistrarChavePixResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${pix.bcb.url}")
interface PixBCBClient {

    @Post("/api/v1/pix/keys")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    fun registrarChavePix(@Body request: RegistrarChavePixRequest):
            HttpResponse<RegistrarChavePixResponse>

    @Delete("/api/v1/pix/keys/{key}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    fun deletarChavePix(@PathVariable key: String, @Body request: DeletarChavePixRequest):
            HttpResponse<DeletarChavePixResponse>

    @Get("/api/v1/pix/keys/{key}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    fun buscarChavePix(@PathVariable key: String) :
            HttpResponse<ChavePixDetalheResponse>

}