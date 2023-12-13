package extensions

import models.scim.*
import org.keycloak.models.GroupModel
import org.keycloak.models.UserModel
import java.util.stream.Collectors

private fun UserModel.scimEmails(): List<ScimMultiValued> {
    val primaryEmail = this.email?.let { ScimMultiValued(it, "other", true) }

    return if (primaryEmail == null) listOf() else listOf(primaryEmail)
}

private fun UserModel.scimGroups(): List<ScimUserGroup> {
    val directGroups = this.groupsStream.collect(Collectors.toList())
    val indirectGroups = directGroups.flatMap { it.parentGroups() }

    fun groupToScimGroup(groupModel: GroupModel, direct: Boolean): ScimUserGroup {
        return ScimUserGroup(
            groupModel.id,
            "Groups/${groupModel.id}",
            groupModel.name,
            if (direct) ScimUserGroupType.DIRECT else ScimUserGroupType.INDIRECT
        )
    }

    return directGroups.map { groupToScimGroup(it, true) } + indirectGroups.map { groupToScimGroup(it, false) }
}

private fun UserModel.scimRoles(): List<ScimMultiValued> {
    return this.roleMappingsStream.map { ScimMultiValued(it.name) }.collect(Collectors.toList())
}

private fun UserModel.scimUserName(): ScimUserName {
    return ScimUserName(familyName = this.lastName, givenName = this.firstName)
}


fun UserModel.toScimUser(
    attributes: List<String> = emptyList(), excludedAttributes: List<String> = emptyList()
): ScimUser {
    val validatedAttributes = attributes + "id" + "userName"
    val validatedExcludedAttributes = excludedAttributes - "id"

    fun listAttr(key: String): List<String>? {
        return this.attributes[key]
    }

    fun singleAttr(key: String): String? {
        return listAttr(key)?.firstOrNull()
    }

    val args = mapOf(
        "id" to { this.id },
        "externalId" to { null },
        "userName" to { this.username },
        "name" to { this.scimUserName() },
        "displayName" to {
            this.attributes["displayName"]?.firstOrNull()
                ?: if (firstName != null && lastName != null) "$firstName $lastName" else username
        },
        "nickName" to { this.attributes["nickName"] },
        "profileUrl" to ::singleAttr,
        "title" to ::singleAttr,
        "userType" to ::singleAttr,
        "preferredLanguage" to ::singleAttr,
        "locale" to ::singleAttr,
        "timezone" to ::singleAttr,
        "active" to { this.isEnabled },
        "password" to { null },
        "emails" to { this.scimEmails() },
        "phoneNumbers" to ::listAttr,
        "ims" to ::listAttr,
        "photos" to ::listAttr,
        "addresses" to ::listAttr,
        "groups" to { this.scimGroups() },
        "entitlements" to ::listAttr,
        "roles" to { this.scimRoles() },
    ).filterKeys { validatedAttributes.contains(it) && !validatedExcludedAttributes.contains(it) }
        .mapValues { it.value(it.key) }

    return ScimUser::class.fromMap(args)

    //    this.attributes.mapKeys {
    //        when (it.key) {
    //            "username" -> "userName"
    //            else -> it.key
    //        }
    //    }.filter { attributes.contains(it.key) }
    //        .filterNot { excludedAttributes.contains(it.key) }.filterNot { it.key == "id" }
    //        .flatMap { attr ->
    //            if (listOf("userName").contains(attr.key)) listOf(attr.key to attr.value.first())
    //            else attr.value.mapIndexed { index, value -> "${attr.key}[$index]" to value }
    //        }.toMap() + mapOf("id" to this.id)

//    return ScimUser(
//        id = this.id,
//        externalId = null,
//        userName = this.username,
//        name = this.scimUserName(),
//        displayName = null,
//        nickName = null,
//        profileUrl = null,
//        title = null,
//        userType = null,
//        preferredLanguage = null,
//        locale = null,
//        timezone = null,
//        active = this.isEnabled,
//        password = null,
//        emails = this.scimEmails(),
//        phoneNumbers = null,
//        ims = null,
//        photos = null,
//        addresses = null,
//        groups = this.scimGroups(),
//        entitlements = null,
//        roles = this.scimRoles(),
//    )
}
