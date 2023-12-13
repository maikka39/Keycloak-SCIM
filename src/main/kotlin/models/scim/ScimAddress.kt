package models.scim

data class ScimAddress(
    val type: String? = null,
    val streetAddress: String? = null,
    val locality: String? = null,
    val region: String? = null,
    val postalCode: String? = null,
    val country: String? = null,
    val formatted: String? = null,
    val primary: Boolean? = null,
)
