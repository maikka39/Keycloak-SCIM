package models.scim

import com.fasterxml.jackson.annotation.JsonProperty

enum class ScimSchemaAttributeUniqueness {
    @JsonProperty("none")
    NONE,

    @JsonProperty("server")
    SERVER,

    @JsonProperty("global")
    GLOBAL,
}
