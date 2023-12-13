package models.scim

data class ScimUserName(
    val formatted: String? = null,
    val familyName: String? = null,
    val givenName: String? = null,
    val middleName: String? = null,
    val honorificPrefix: String? = null,
    val honorificSuffix: String? = null,
)
