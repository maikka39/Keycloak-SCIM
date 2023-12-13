package models.scim

import com.fasterxml.jackson.annotation.JsonProperty

data class ScimMultiValued(
    val value: String,
    val type: String? = null,
    val primary: Boolean? = null,
    val display: String? = null,
    @field:JsonProperty(value = "\$ref") val ref: String? = null,
)
