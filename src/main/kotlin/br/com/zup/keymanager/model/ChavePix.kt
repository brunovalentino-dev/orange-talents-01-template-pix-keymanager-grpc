package br.com.zup.keymanager.model

import br.com.zup.TipoConta
import br.com.zup.TipoChavePix
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class ChavePix(
    @field:NotNull
    @Column(nullable = false)
    val clienteId: UUID,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoChavePix: TipoChavePix,

    @field:NotBlank
    @Column(unique = true, nullable = false)
    val chavePix: String,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoConta: TipoConta,

    @field:Valid
    @Embedded
    val conta: Conta
)
{
    @Id
    @GeneratedValue
    val id: UUID? = null

    @Column(nullable = false)
    val criadoEm: LocalDateTime = LocalDateTime.now()

    override fun toString(): String {
        return "ChavePix(clienteId=$clienteId, tipoChavePix=$tipoChavePix, chavePix='$chavePix', " +
                "tipoConta=$tipoConta, conta=$conta, id=$id, criadoEm=$criadoEm)"
    }
}