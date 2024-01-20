package models.scim

data class ScimFilterSupport(
    val supported: Boolean,
    val maxResults: Int,
)
