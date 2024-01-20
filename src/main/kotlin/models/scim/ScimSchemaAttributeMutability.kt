package models.scim

import com.fasterxml.jackson.annotation.JsonProperty

enum class ScimSchemaAttributeMutability {
    @JsonProperty("readOnly")
    READ_ONLY,

    @JsonProperty("readWrite")
    READ_WRITE,

    @JsonProperty("immutable")
    IMMUTABLE,

    @JsonProperty("writeOnly")
    WRITE_ONLY,
}