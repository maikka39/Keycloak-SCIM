package models.scim

interface ScimCommon {
    val schemas: List<String>
    val meta: ScimMeta?
}
