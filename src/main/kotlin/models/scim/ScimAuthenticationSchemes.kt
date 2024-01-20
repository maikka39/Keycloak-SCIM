package models.scim

data class ScimAuthenticationSchemes(
//    val type: ??,
    val name: String,
    val description: String,
    val specUri: String?,
    val documentationUri: String?,
)
