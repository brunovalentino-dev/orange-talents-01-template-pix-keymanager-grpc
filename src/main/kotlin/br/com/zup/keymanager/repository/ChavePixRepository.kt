package br.com.zup.keymanager.repository

import br.com.zup.keymanager.model.ChavePix
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface ChavePixRepository : CrudRepository<ChavePix, UUID> {

    fun existsByChavePix(chavePix: String?): Boolean
    fun findByIdAndClienteId(chavePixUUID: UUID?, clienteIdUUID: UUID?): Optional<ChavePix>
    fun findAllByClienteId(clienteIdUUID: UUID?): List<ChavePix>
    fun findByChavePix(chavePix: String?): Optional<ChavePix>

}