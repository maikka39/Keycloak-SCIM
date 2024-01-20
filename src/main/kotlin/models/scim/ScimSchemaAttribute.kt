package models.scim

data class ScimSchemaAttribute(
    val name: String,
    val type: ScimSchemaAttributeType,
    val subAttributes: List<ScimSchemaAttribute>? = null,
    val multiValued: Boolean,
    val description: String? = null,
    val required: Boolean? = null,
    val canonicalValues: List<String>? = null,
    val caseExact: Boolean? = null, // TODO: Actually support case-insensitive fields
    val mutability: ScimSchemaAttributeMutability? = null,
    val returned: ScimSchemaAttributeReturn? = null,
    val uniqueness:ScimSchemaAttributeUniqueness? = null,
    val referenceTypes: List<String>? = null,
)
