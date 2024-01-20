package models.scim

import com.fasterxml.jackson.annotation.JsonProperty

data class ScimUserManager(
    val value: String,
    @field:JsonProperty(value = "\$ref") val ref: String? = null,
    val displayName: String? = null,
)
