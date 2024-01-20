package models.scim

data class ScimServiceProviderConfig(
    val documentationUri: String? = null,
    val patch: ScimSupported,
    val bulk: ScimBulkSupport,
    val filter: ScimFilterSupport,
    val changePassword: ScimSupported,
    val sort: ScimSupported,
    val etag: ScimSupported,
    val authenticationSchemes: ScimAuthenticationSchemes,
    override val schemas: List<String> = listOf("urn:ietf:params:scim:schemas:core:2.0:ServiceProviderConfig"),
    override val meta: ScimMeta? = null,
) : ScimCommon {
    companion object {
        val schema by lazy {
            ScimSchema(
                id = "urn:ietf:params:scim:schemas:core:2.0:ServiceProviderConfig",
                name = "Service Provider Configuration",
                description = "Schema for representing the service provider's configuration",
                attributes = listOf(
                    ScimSchemaAttribute(
                        name = "documentationUri",
                        type = ScimSchemaAttributeType.REFERENCE,
                        referenceTypes = listOf("external"),
                        multiValued = false,
                        description = "An HTTP-addressable URL pointing to the service provider's human-consumable help documentation.",
                        required = false,
                        caseExact = false,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                    ),
                    ScimSchemaAttribute(
                        name = "patch",
                        type = ScimSchemaAttributeType.COMPLEX,
                        multiValued = false,
                        description = "A complex type that specifies PATCH configuration options.",
                        required = true,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        subAttributes = listOf(
                            ScimSchemaAttribute(
                                name = "supported",
                                type = ScimSchemaAttributeType.BOOLEAN,
                                multiValued = false,
                                description = "A Boolean value specifying whether or not the operation is supported.",
                                required = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT
                            )
                        )
                    ),
                    ScimSchemaAttribute(
                        name = "bulk",
                        type = ScimSchemaAttributeType.COMPLEX,
                        multiValued = false,
                        description = "A complex type that specifies bulk configuration options.",
                        required = true,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        subAttributes = listOf(
                            ScimSchemaAttribute(
                                name = "supported",
                                type = ScimSchemaAttributeType.BOOLEAN,
                                multiValued = false,
                                description = "A Boolean value specifying whether or not the operation is supported.",
                                required = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT
                            ),
                            ScimSchemaAttribute(
                                name = "maxOperations",
                                type = ScimSchemaAttributeType.INTEGER,
                                multiValued = false,
                                description = "An integer value specifying the maximum number of operations.",
                                required = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                            ScimSchemaAttribute(
                                name = "maxPayloadSize",
                                type = ScimSchemaAttributeType.INTEGER,
                                multiValued = false,
                                description = "An integer value specifying the maximum payload size in bytes.",
                                required = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            )
                        )
                    ),
                    ScimSchemaAttribute(
                        name = "filter",
                        type = ScimSchemaAttributeType.COMPLEX,
                        multiValued = false,
                        description = "A complex type that specifies FILTER options.",
                        required = true,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        subAttributes = listOf(
                            ScimSchemaAttribute(
                                name = "supported",
                                type = ScimSchemaAttributeType.BOOLEAN,
                                multiValued = false,
                                description = "A Boolean value specifying whether or not the operation is supported.",
                                required = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT
                            ),
                            ScimSchemaAttribute(
                                name = "maxResults",
                                type = ScimSchemaAttributeType.INTEGER,
                                multiValued = false,
                                description = "An integer value specifying the maximum number of resources returned in a response.",
                                required = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            )
                        )
                    ),
                    ScimSchemaAttribute(
                        name = "changePassword",
                        type = ScimSchemaAttributeType.COMPLEX,
                        multiValued = false,
                        description = "A complex type that specifies configuration options related to changing a password.",
                        required = true,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        subAttributes = listOf(
                            ScimSchemaAttribute(
                                name = "supported",
                                type = ScimSchemaAttributeType.BOOLEAN,
                                multiValued = false,
                                description = "A Boolean value specifying whether or not the operation is supported.",
                                required = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT
                            )
                        )
                    ),
                    ScimSchemaAttribute(
                        name = "sort",
                        type = ScimSchemaAttributeType.COMPLEX,
                        multiValued = false,
                        description = "A complex type that specifies sort result options.",
                        required = true,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        subAttributes = listOf(
                            ScimSchemaAttribute(
                                name = "supported",
                                type = ScimSchemaAttributeType.BOOLEAN,
                                multiValued = false,
                                description = "A Boolean value specifying whether or not the operation is supported.",
                                required = true,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT
                            )
                        )
                    ),
                    ScimSchemaAttribute(
                        name = "authenticationSchemes",
                        type = ScimSchemaAttributeType.COMPLEX,
                        multiValued = true,
                        description = "A complex type that specifies supported authentication scheme properties.",
                        required = true,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        mutability = ScimSchemaAttributeMutability.READ_ONLY,
                        subAttributes = listOf(
                            ScimSchemaAttribute(
                                name = "name",
                                type = ScimSchemaAttributeType.STRING,
                                multiValued = false,
                                description = "The common authentication scheme name, e.g., HTTP Basic.",
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
                                description = "A description of the authentication scheme.",
                                required = true,
                                caseExact = false,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                            ScimSchemaAttribute(
                                name = "specUri",
                                type = ScimSchemaAttributeType.REFERENCE,
                                referenceTypes = listOf("external"),
                                multiValued = false,
                                description = "An HTTP-addressable URL pointing to the authentication scheme's specification.",
                                required = false,
                                caseExact = false,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                            ScimSchemaAttribute(
                                name = "documentationUri",
                                type = ScimSchemaAttributeType.REFERENCE,
                                referenceTypes = listOf("external"),
                                multiValued = false,
                                description = "An HTTP-addressable URL pointing to the authentication scheme's usage documentation.",
                                required = false,
                                caseExact = false,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                        ),
                    ),
                ),
            )
        }
    }
}
