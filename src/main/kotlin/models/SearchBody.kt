package models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.regex.Pattern
import kotlin.math.max
import kotlin.math.min

@JsonIgnoreProperties(ignoreUnknown = true)
data class SearchBody(
    val startIndex: Long,
    val count: Long,
    val attributes: List<String>,
    val excludedAttributes: List<String>,
    val filter: Pair<String, String>?,
) {
    constructor(
        @JsonProperty("startIndex") startIndex: Long? = null,
        @JsonProperty("count") count: Long? = null,
        @JsonProperty("attributes") attributes: String? = null,
        @JsonProperty("excludedAttributes") excludedAttributes: String? = null,
        @JsonProperty("filter") filter: String? = null,
    ) : this(
        max(1, startIndex ?: 1) - 1,
        min(max(0, count ?: 100), 1000),
        (attributes ?: "").split(','),
        (excludedAttributes ?: "").split(','),
        filter?.let {
            val regex = "(\\w+) eq \"([^\"]*)\""
            val response = Pattern.compile(regex)
            val match = response.matcher(it)
            val found = match.find()
            return@let (if (found) match.group(1) to match.group(2) else null)
        },
    )
}
