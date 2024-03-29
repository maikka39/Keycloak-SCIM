package models.scim

data class ScimSchema(
    val id: String,
    val name: String? = null,
    val description: String? = null,
    val attributes: List<ScimSchemaAttribute>,
    override val schemas: List<String> = listOf("urn:ietf:params:scim:schemas:core:2.0:Schema"),
    override val meta: ScimMeta? = null,
) : ScimCommon {
    companion object {
        val schema by lazy {
            ScimSchema(
                id = "urn:ietf:params:scim:schemas:core:2.0:Schema",
                name = "Schema",
                description = "Specifies the schema that describes a SCIM schema",
                attributes = listOf(ScimSchemaAttribute(
                    name = "id",
                    type = ScimSchemaAttributeType.STRING,
                    multiValued = false,
                    description = "The unique URI of the schema. When applicable, service providers MUST specify the URI.",
                    required = true,
                    caseExact = false,
                    mutability = ScimSchemaAttributeMutability.READ_ONLY,
                    returned = ScimSchemaAttributeReturn.DEFAULT,
                    uniqueness = ScimSchemaAttributeUniqueness.NONE,
                ),
                    ScimSchemaAttribute(
                        name = "name",
                        type = ScimSchemaAttributeType.STRING,
                        multiValued = false,
                        description = "The schema's human-readable name.  When applicable, service providers MUST specify the name, e.g., 'User'.",
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
                        description = "The schema's human-readable name.  When applicable, service providers MUST specify the name, e.g., 'User'.",
                        required = false,
                        caseExact = false,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                    ),
                    ScimSchemaAttribute(
                        name = "attributes",
                        type = ScimSchemaAttributeType.COMPLEX,
                        multiValued = true,
                        description = "A complex attribute that includes the attributes of a schema.",
                        required = true,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        subAttributes = listOf(
                            ScimSchemaAttribute(
                                name = "name",
                                type = ScimSchemaAttributeType.STRING,
                                multiValued = false,
                                description = "The attribute's name.",
                                required = true,
                                caseExact = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                            ScimSchemaAttribute(
                                name = "type",
                                type = ScimSchemaAttributeType.STRING,
                                multiValued = false,
                                description = "The attribute's data type. Valid values include 'string', 'complex', 'boolean', 'decimal', 'integer', 'dateTime', 'reference'.",
                                required = true,
                                canonicalValues = listOf(
                                    "string",
                                    "complex",
                                    "boolean",
                                    "decimal",
                                    "integer",
                                    "dateTime",
                                    "reference"
                                ),
                                caseExact = false,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                            ScimSchemaAttribute(
                                name = "multiValued",
                                type = ScimSchemaAttributeType.BOOLEAN,
                                multiValued = false,
                                description = "A Boolean value indicating an attribute's plurality.",
                                required = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                            ),
                            ScimSchemaAttribute(
                                name = "description",
                                type = ScimSchemaAttributeType.STRING,
                                multiValued = false,
                                description = "A human-readable description of the attribute.",
                                required = false,
                                caseExact = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                            ScimSchemaAttribute(
                                name = "required",
                                type = ScimSchemaAttributeType.BOOLEAN,
                                multiValued = false,
                                description = "A boolean value indicating whether or not the attribute is required.",
                                required = false,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                            ),
                            ScimSchemaAttribute(
                                name = "canonicalValues",
                                type = ScimSchemaAttributeType.STRING,
                                multiValued = true,
                                description = "A collection of canonical values.  When applicable, service providers MUST specify the canonical types, e.g., 'work', 'home'.",
                                required = false,
                                caseExact = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                            ScimSchemaAttribute(
                                name = "caseExact",
                                type = ScimSchemaAttributeType.BOOLEAN,
                                multiValued = false,
                                description = "A Boolean value indicating whether or not a string attribute is case sensitive.",
                                required = false,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                            ),
                            ScimSchemaAttribute(
                                name = "mutability",
                                type = ScimSchemaAttributeType.STRING,
                                multiValued = false,
                                description = "Indicates whether or not an attribute is modifiable.",
                                required = false,
                                caseExact = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                                canonicalValues = listOf(
                                    "readOnly",
                                    "readWrite",
                                    "immutable",
                                    "writeOnly"
                                )
                            ),
                            ScimSchemaAttribute(
                                name = "returned",
                                type = ScimSchemaAttributeType.STRING,
                                multiValued = false,
                                description = "Indicates when an attribute is returned in a response (e.g., to a query).",
                                required = false,
                                caseExact = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                                canonicalValues = listOf(
                                    "always",
                                    "never",
                                    "default",
                                    "request"
                                )
                            ),
                            ScimSchemaAttribute(
                                name = "uniqueness",
                                type = ScimSchemaAttributeType.STRING,
                                multiValued = false,
                                description = "Indicates how unique a value must be.",
                                required = false,
                                caseExact = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                                canonicalValues = listOf(
                                    "none",
                                    "server",
                                    "global"
                                )
                            ),
                            ScimSchemaAttribute(
                                name = "referenceTypes",
                                type = ScimSchemaAttributeType.STRING,
                                multiValued = true,
                                description = "Used only with an attribute of type 'reference'.  Specifies a SCIM resourceType that a reference attribute MAY refer to, e.g., 'User'.",
                                required = false,
                                caseExact = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                            ScimSchemaAttribute(
                                name = "subAttributes",
                                type = ScimSchemaAttributeType.COMPLEX,
                                multiValued = true,
                                description = "Used to define the sub-attributes of a complex attribute.",
                                required = false,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                subAttributes = listOf(
                                    ScimSchemaAttribute(
                                        name = "name",
                                        type = ScimSchemaAttributeType.STRING,
                                        multiValued = false,
                                        description = "The attribute's name.",
                                        required = true,
                                        caseExact = true,
                                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                        returned = ScimSchemaAttributeReturn.DEFAULT,
                                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                                    ),
                                    ScimSchemaAttribute(
                                        name = "type",
                                        type = ScimSchemaAttributeType.STRING,
                                        multiValued = false,
                                        description = "The attribute's data type. Valid values include 'string', 'complex', 'boolean', 'decimal', 'integer', 'dateTime', 'reference'.",
                                        required = true,
                                        caseExact = false,
                                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                        returned = ScimSchemaAttributeReturn.DEFAULT,
                                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                                        canonicalValues = listOf(
                                            "string",
                                            "complex",
                                            "boolean",
                                            "decimal",
                                            "integer",
                                            "dateTime",
                                            "reference"
                                        )
                                    ),
                                    ScimSchemaAttribute(
                                        name = "multiValued",
                                        type = ScimSchemaAttributeType.BOOLEAN,
                                        multiValued = false,
                                        description = "A Boolean value indicating an attribute's plurality.",
                                        required = true,
                                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                        returned = ScimSchemaAttributeReturn.DEFAULT
                                    ),
                                    ScimSchemaAttribute(
                                        name = "description",
                                        type = ScimSchemaAttributeType.STRING,
                                        multiValued = false,
                                        description = "A human-readable description of the attribute.",
                                        required = false,
                                        caseExact = true,
                                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                        returned = ScimSchemaAttributeReturn.DEFAULT,
                                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                                    ),
                                    ScimSchemaAttribute(
                                        name = "required",
                                        type = ScimSchemaAttributeType.BOOLEAN,
                                        multiValued = false,
                                        description = "A boolean value indicating whether or not the attribute is required.",
                                        required = false,
                                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                        returned = ScimSchemaAttributeReturn.DEFAULT
                                    ),
                                    ScimSchemaAttribute(
                                        name = "canonicalValues",
                                        type = ScimSchemaAttributeType.STRING,
                                        multiValued = true,
                                        description = "A collection of canonical values. When applicable, service providers MUST specify the canonical types, e.g., 'work', 'home'.",
                                        required = false,
                                        caseExact = true,
                                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                        returned = ScimSchemaAttributeReturn.DEFAULT,
                                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                                    ),
                                    ScimSchemaAttribute(
                                        name = "caseExact",
                                        type = ScimSchemaAttributeType.BOOLEAN,
                                        multiValued = false,
                                        description = "A Boolean value indicating whether or not a string attribute is case sensitive.",
                                        required = false,
                                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                        returned = ScimSchemaAttributeReturn.DEFAULT
                                    ),
                                    ScimSchemaAttribute(
                                        name = "mutability",
                                        type = ScimSchemaAttributeType.STRING,
                                        multiValued = false,
                                        description = "Indicates whether or not an attribute is modifiable.",
                                        required = false,
                                        caseExact = true,
                                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                        returned = ScimSchemaAttributeReturn.DEFAULT,
                                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                                        canonicalValues = listOf(
                                            "readOnly",
                                            "readWrite",
                                            "immutable",
                                            "writeOnly"
                                        )
                                    ),
                                    ScimSchemaAttribute(
                                        name = "returned",
                                        type = ScimSchemaAttributeType.STRING,
                                        multiValued = false,
                                        description = "Indicates when an attribute is returned in a response (e.g., to a query).",
                                        required = false,
                                        caseExact = true,
                                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                        returned = ScimSchemaAttributeReturn.DEFAULT,
                                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                                        canonicalValues = listOf(
                                            "always",
                                            "never",
                                            "default",
                                            "request"
                                        )
                                    ),
                                    ScimSchemaAttribute(
                                        name = "uniqueness",
                                        type = ScimSchemaAttributeType.STRING,
                                        multiValued = false,
                                        description = "Indicates how unique a value must be.",
                                        required = false,
                                        caseExact = true,
                                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                        returned = ScimSchemaAttributeReturn.DEFAULT,
                                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                                        canonicalValues = listOf(
                                            "none",
                                            "server",
                                            "global"
                                        )
                                    ),
                                    ScimSchemaAttribute(
                                        name = "referenceTypes",
                                        type = ScimSchemaAttributeType.STRING,
                                        multiValued = false,
                                        description = "Used only with an attribute of type 'reference'.  Specifies a SCIM resourceType that a reference attribute MAY refer to, e.g., 'User'.",
                                        required = false,
                                        caseExact = true,
                                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                        returned = ScimSchemaAttributeReturn.DEFAULT,
                                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                                    ),
                                ),
                            ),
                        ),
                    )),
            )
        }
    }
}
