package models.scim

data class ScimGroup(
    val id: String,
    val externalId: String? = null,
    val displayName: String? = null,
    val members: List<ScimMultiValued>? = null,
    override val schemas: List<String> = listOf("urn:ietf:params:scim:schemas:core:2.0:Group"),
    override val meta: ScimMeta? = null,
) : ScimCommon {
    companion object {
        val resourceType by lazy {
            ScimResourceType(
                id = "Group",
                name = "Group",
                description = "Group",
                endpoint = "/Groups",
                schema = "urn:ietf:params:scim:schemas:core:2.0:Group",
            )
        }
        val schema by lazy {
            ScimSchema(
                id = "urn:ietf:params:scim:schemas:core:2.0:Group",
                name = "Group",
                description = "Group",
                attributes = listOf(
                    ScimSchemaAttribute(
                        name = "displayName",
                        type = ScimSchemaAttributeType.STRING,
                        multiValued = false,
                        description = "A human-readable name for the Group. REQUIRED.",
                        required = false,
                        caseExact = false,
                        mutability = ScimSchemaAttributeMutability.READ_WRITE,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                    ),
                    ScimSchemaAttribute(
                        name = "members",
                        type = ScimSchemaAttributeType.COMPLEX,
                        multiValued = true,
                        description = "A list of members of the Group.",
                        required = false,
                        subAttributes = listOf(
                            ScimSchemaAttribute(
                                name = "value",
                                type = ScimSchemaAttributeType.STRING,
                                multiValued = false,
                                description = "Identifier of the member of this Group.",
                                required = false,
                                caseExact = false,
                                mutability = ScimSchemaAttributeMutability.IMMUTABLE,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                            ScimSchemaAttribute(
                                name = "\$ref",
                                type = ScimSchemaAttributeType.REFERENCE,
                                referenceTypes = listOf(
                                    "User",
                                    "Group"
                                ),
                                multiValued = false,
                                description = "The URI corresponding to a SCIM resource that is a member of this Group.",
                                required = false,
                                caseExact = false,
                                mutability = ScimSchemaAttributeMutability.IMMUTABLE,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                            ScimSchemaAttribute(
                                name = "type",
                                type = ScimSchemaAttributeType.STRING,
                                multiValued = false,
                                description = "A label indicating the type of resource, e.g., 'User' or 'Group'.",
                                required = false,
                                caseExact = false,
                                canonicalValues = listOf(
                                    "User",
                                    "Group"
                                ),
                                mutability = ScimSchemaAttributeMutability.IMMUTABLE,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                        ),
                        mutability = ScimSchemaAttributeMutability.READ_WRITE,
                        returned = ScimSchemaAttributeReturn.DEFAULT
                    ),
                ),
            )
        }
    }
}
