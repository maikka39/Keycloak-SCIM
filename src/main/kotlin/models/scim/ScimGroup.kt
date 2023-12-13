package models.scim

data class ScimGroup(
    override val id: String,
    override val externalId: String? = null,
    val displayName: String? = null,
    val members: List<ScimMultiValued>? = null,
) : ScimCommon
