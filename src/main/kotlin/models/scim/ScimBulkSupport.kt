package models.scim

data class ScimBulkSupport(
    val supported: Boolean,
    val maxOperations: Int,
    val maxPayloadSize: Int,
)
