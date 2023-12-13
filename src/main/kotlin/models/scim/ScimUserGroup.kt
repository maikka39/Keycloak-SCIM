package models.scim

import com.fasterxml.jackson.annotation.JsonProperty

data class ScimUserGroup(
    val value: String,
    @field:JsonProperty(value = "\$ref") val ref: String,
    val display: String,
    val type: ScimUserGroupType,
)
