package models.scim

import com.fasterxml.jackson.annotation.JsonProperty

enum class ScimUserGroupType {
    @JsonProperty("direct")
    DIRECT,

    @JsonProperty("indirect")
    INDIRECT,
}
