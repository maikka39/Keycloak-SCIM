package models

import com.fasterxml.jackson.annotation.JsonProperty

data class ListResponse(
    val totalResults: Long,
    val itemsPerPage: Long,
    val startIndex: Long,
    @field:JsonProperty(value = "Resources")
    val resources: List<Map<String, String>>,
    val schemas: Set<String> = setOf(
        "urn:ietf:params:scim:api:messages:2.0:ListResponse",
    ),
) {
    operator fun plus(other: ListResponse): ListResponse {
        return ListResponse(
            totalResults + other.totalResults,
            itemsPerPage + other.itemsPerPage,
            startIndex,
            resources + other.resources,
            schemas + other.schemas
        )
    }
}
