package models

import com.fasterxml.jackson.annotation.JsonProperty

data class ListResponseResource(
    val id: String,
    val userName: String,
)

data class ListResponse(
    val totalResults: Long,
    val itemsPerPage: Long,
    val startIndex: Long,
    @field:JsonProperty(value = "Resources")
    val resources: List<Map<String, String>>,
    val schemas: List<String> = listOf(
        "urn:ietf:params:scim:api:messages:2.0:ListResponse",
    ),
)
