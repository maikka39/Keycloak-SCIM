package models.scim

import com.fasterxml.jackson.annotation.JsonProperty

enum class ScimSchemaAttributeReturn {
    @JsonProperty("always")
    ALWAYS,

    @JsonProperty("never")
    NEVER,

    @JsonProperty("default")
    DEFAULT,

    @JsonProperty("request")
    REQUEST,
}