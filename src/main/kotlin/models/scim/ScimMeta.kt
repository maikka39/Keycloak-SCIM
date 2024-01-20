package models.scim

import org.apache.james.mime4j.dom.datetime.DateTime

data class ScimMeta(
    val resourceType: String,
    val created: DateTime? = null,
    val lastModified: DateTime? = null,
    val location: String,
    val version: String? = null,
)
