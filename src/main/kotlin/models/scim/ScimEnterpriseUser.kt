package models.scim


data class ScimEnterpriseUser(
    val id: String,
    val externalId: String? = null,
    val employeeNumber: String? = null,
    val costCenter: String? = null,
    val organization: String? = null,
    val division: String? = null,
    val department: String? = null,
    val manager: ScimUserManager? = null,
    override val schemas: List<String> = listOf("urn:ietf:params:scim:schemas:extension:enterprise:2.0:User"),
    override val meta: ScimMeta? = null,
) : ScimCommon {
    companion object {
        val schema by lazy {
            ScimSchema(
                id = "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User",
                name = "EnterpriseUser",
                description = "Enterprise User",
                attributes = listOf(
                    ScimSchemaAttribute(
                        name = "employeeNumber",
                        type = ScimSchemaAttributeType.STRING,
                        multiValued = false,
                        description = "Numeric or alphanumeric identifier assigned to a person, typically based on order of hire or association with an organization.",
                        required = false,
                        caseExact = false,
                        mutability = ScimSchemaAttributeMutability.READ_WRITE,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                    ),
                    ScimSchemaAttribute(
                        name = "costCenter",
                        type = ScimSchemaAttributeType.STRING,
                        multiValued = false,
                        description = "Identifies the name of a cost center.",
                        required = false,
                        caseExact = false,
                        mutability = ScimSchemaAttributeMutability.READ_WRITE,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                    ),
                    ScimSchemaAttribute(
                        name = "organization",
                        type = ScimSchemaAttributeType.STRING,
                        multiValued = false,
                        description = "Identifies the name of an organization.",
                        required = false,
                        caseExact = false,
                        mutability = ScimSchemaAttributeMutability.READ_WRITE,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                    ),
                    ScimSchemaAttribute(
                        name = "division",
                        type = ScimSchemaAttributeType.STRING,
                        multiValued = false,
                        description = "Identifies the name of a division.",
                        required = false,
                        caseExact = false,
                        mutability = ScimSchemaAttributeMutability.READ_WRITE,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                    ),
                    ScimSchemaAttribute(
                        name = "department",
                        type = ScimSchemaAttributeType.STRING,
                        multiValued = false,
                        description = "Identifies the name of a department.",
                        required = false,
                        caseExact = false,
                        mutability = ScimSchemaAttributeMutability.READ_WRITE,
                        returned = ScimSchemaAttributeReturn.DEFAULT,
                        uniqueness = ScimSchemaAttributeUniqueness.NONE,
                    ),
                    ScimSchemaAttribute(
                        name = "manager",
                        type = ScimSchemaAttributeType.COMPLEX,
                        multiValued = false,
                        description = "The User's manager.  A complex type that optionally allows service providers to represent organizational hierarchy by referencing the 'id' attribute of another User.",
                        required = false,
                        subAttributes = listOf(
                            ScimSchemaAttribute(
                                name = "value",
                                type = ScimSchemaAttributeType.STRING,
                                multiValued = false,
                                description = "The id of the SCIM resource representing the User's manager.  REQUIRED.",
                                required = false,
                                caseExact = false,
                                mutability = ScimSchemaAttributeMutability.READ_WRITE,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                            ScimSchemaAttribute(
                                name = "\$ref",
                                type = ScimSchemaAttributeType.REFERENCE,
                                referenceTypes = listOf(
                                    "User"
                                ),
                                multiValued = false,
                                description = "The URI of the SCIM resource representing the User's manager.  REQUIRED.",
                                required = false,
                                caseExact = false,
                                mutability = ScimSchemaAttributeMutability.READ_WRITE,
                                returned = ScimSchemaAttributeReturn.DEFAULT,
                                uniqueness = ScimSchemaAttributeUniqueness.NONE,
                            ),
                            ScimSchemaAttribute(
                                name = "displayName",
                                type = ScimSchemaAttributeType.STRING,
                                multiValued = false,
                                description = "The displayName of the User's manager. OPTIONAL and READ-ONLY.",
                                required = false,
                                caseExact = false,
                                mutability = ScimSchemaAttributeMutability.READ_ONLY,
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

