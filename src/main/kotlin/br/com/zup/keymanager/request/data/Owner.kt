package br.com.zup.keymanager.request.data

data class Owner (
    val type: OwnerType,
    val name: String,
    val taxIdNumber: String
)
{
    enum class OwnerType {
        LEGAL_PERSON,
        NATURAL_PERSON
    }
}