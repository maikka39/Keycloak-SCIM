package models.scim

import com.fasterxml.jackson.annotation.JsonProperty


data class ScimUser(
    override val id: String,
    override val externalId: String? = null,
    val userName: String? = null,
    val name: ScimUserName? = null,
    val displayName: String? = null,
    val nickName: String? = null,
    val profileUrl: String? = null,
    val title: String? = null,
    val userType: String? = null,
    val preferredLanguage: String? = null,
    val locale: String? = null,
    val timezone: String? = null,
    val active: Boolean? = null,
    @field:JsonProperty(access = JsonProperty.Access.WRITE_ONLY) val password: String? = null,
    val emails: List<ScimMultiValued>? = null,
    val phoneNumbers: List<ScimMultiValued>? = null,
    val ims: List<ScimMultiValued>? = null,
    val photos: List<ScimMultiValued>? = null,
    val addresses: List<ScimAddress>? = null,
    @field:JsonProperty(access = JsonProperty.Access.READ_ONLY) val groups: List<ScimUserGroup>? = null,
    val entitlements: List<ScimMultiValued>? = null,
    val roles: List<ScimMultiValued>? = null,
    val x509Certificates: List<ScimMultiValued>? = null,
    val schemas: List<String> = listOf("urn:ietf:params:scim:schemas:core:2.0:User")
) : ScimCommon
