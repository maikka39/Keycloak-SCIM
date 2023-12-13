package models.scim

import com.fasterxml.jackson.annotation.JsonProperty

data class ScimListResponse(
    val totalResults: Long,
    val itemsPerPage: Long,
    val startIndex: Long,
    @field:JsonProperty(value = "Resources") val resources: List<ScimCommon>,
    val schemas: Set<String> = setOf(
        "urn:ietf:params:scim:api:messages:2.0:ListResponse",
    ),
) {
    operator fun plus(other: ScimListResponse): ScimListResponse {
        return ScimListResponse(
            totalResults + other.totalResults,
            itemsPerPage + other.itemsPerPage,
            startIndex,
            resources + other.resources,
            schemas + other.schemas
        )
    }
}
