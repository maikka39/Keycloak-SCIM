package models.scim

data class ScimResourceType(
    val id: String,
    val name: String,
    val description: String? = null,
    val endpoint: String,
    val schema: String,
    val schemaExtensions: List<ScimSchemaExtension>? = null,
    override val schemas: List<String> = listOf("urn:ietf:params:scim:schemas:core:2.0:ResourceType"),
    override val meta: ScimMeta? = null,
) : ScimCommon {
    companion object {
        val schema by lazy {
            ScimSchema(
                id = "urn:ietf:params:scim:schemas:core:2.0:ResourceType",
                name = "ResourceType",
                description = "Specifies the schema that describes a SCIM resource type",
                attributes = listOf(
                    ScimSchemaAttribute(
                        name = "id",
                        type = ScimSchemaAttributeType.STRING,
                        multiValued = false,
                        description = "The resource type's server unique id. May be the same as the 'name' attribute.",
                        required = false,
                        caseExact = false,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                    ),
                    ScimSchemaAttribute(
                        name = "name",
                        type = ScimSchemaAttributeType.STRING,
                        multiValued = false,
                        description = "The resource type name.  When applicable, service providers MUST specify the name, e.g., 'User'.",
                        required = true,
                        caseExact = false,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                    ),
                    ScimSchemaAttribute(
                        name = "description",
                        type = ScimSchemaAttributeType.STRING,
                        multiValued = false,
                        description = "The resource type's human-readable description.  When applicable, service providers MUST specify the description.",
                        required = false,
                        caseExact = false,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                    ),
                    ScimSchemaAttribute(
                        name = "endpoint",
                        type = ScimSchemaAttributeType.REFERENCE,
                        referenceTypes = listOf("uri"),
                        multiValued = false,
                        description = "The resource type's HTTP-addressable endpoint relative to the Base URL, e.g., '/Users'.",
                        required = true,
                        caseExact = false,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                    ),
                    ScimSchemaAttribute(
                        name = "schema",
                        type = ScimSchemaAttributeType.REFERENCE,
                        referenceTypes = listOf("uri"),
                        multiValued = false,
                        description = "The resource type's primary/base schema URI.",
                        required = true,
                        caseExact = true,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                    ),
                    ScimSchemaAttribute(
                        name = "schemaExtensions",
                        type = ScimSchemaAttributeType.COMPLEX,
                        multiValued = false,
                        description = "A list of URIs of the resource type's schema extensions.",
                        required = true,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        subAttributes = listOf(
                            ScimSchemaAttribute(
                                name = "schema",
                                type = ScimSchemaAttributeType.REFERENCE,
                                referenceTypes = listOf("uri"),
                                multiValued = false,
                                description = "The URI of a schema extension.",
                                required = true,
                                caseExact = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                            ScimSchemaAttribute(
                                name = "required",
                                type = ScimSchemaAttributeType.BOOLEAN,
                                multiValued = false,
                                description = "A Boolean value that specifies whether or not the schema extension is required for the resource type.  If true, a resource of this type MUST include this schema extension and also include any attributes declared as required in this schema extension. If false, a resource of this type MAY omit this schema extension.",
                                required = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT
                            ),
                        ),
                    ),
                ),
            )
        }
    }
}
