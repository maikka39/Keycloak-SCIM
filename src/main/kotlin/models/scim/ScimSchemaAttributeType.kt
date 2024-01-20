package models.scim

import com.fasterxml.jackson.annotation.JsonProperty

enum class ScimSchemaAttributeType {
    @JsonProperty("string")
    STRING,

    @JsonProperty("boolean")
    BOOLEAN,

    @JsonProperty("decimal")
    DECIMAL,

    @JsonProperty("integer")
    INTEGER,

    @JsonProperty("dateTime")
    DATE_TIME,

    @JsonProperty("reference")
    REFERENCE,

    @JsonProperty("complex")
    COMPLEX,
}