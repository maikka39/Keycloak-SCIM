package extensions

import models.scim.ScimGroup
import models.scim.ScimMultiValued
import org.keycloak.models.GroupModel
import org.keycloak.models.KeycloakSession
import java.util.stream.Collectors

private fun GroupModel.parentGroups(children: List<GroupModel>): List<GroupModel> {
    if (this.parent == null) return children + this

    return this.parent.parentGroups(children) + this
}

fun GroupModel.parentGroups(): List<GroupModel> {
    return this.parentGroups(listOf()).dropLast(1)
}

fun GroupModel.toScimGroup(
    session: KeycloakSession, attributes: List<String> = emptyList(), excludedAttributes: List<String> = emptyList()
): ScimGroup {
    val validatedAttributes = attributes + "id" + "displayName"
    val validatedExcludedAttributes = excludedAttributes - "id"

    val args = mapOf(
        "id" to { this.id },
        "externalId" to { null },
        "displayName" to { this.name },
        "members" to {
            session.users().getGroupMembersStream(session.context.realm, this)
                .map { it.toScimUser(listOf("displayName")) }
                .map { ScimMultiValued(value = it.id, type = "User", display = it.displayName, ref = "Users/${it.id}") }
                .collect(Collectors.toList()) + this.subGroupsStream.collect(Collectors.toList())
                .map { it.toScimGroup(session, listOf("displayName")) }.map {
                    ScimMultiValued(
                        value = it.id, type = "Group", display = it.displayName, ref = "Groups/${it.id}"
                    )
                }
        },
    ).filterKeys { validatedAttributes.contains(it) && !validatedExcludedAttributes.contains(it) }
        .mapValues { it.value() }

    return ScimGroup::class.fromMap(args)

//    (this.attributes + mapOf(
//        "name" to listOf(this.name),
//        "members" to (if (attributes.contains("members")) session.users().getGroupMembersStream(session.context.realm, this)
//            .map { it.id }.collect(Collectors.toList()) else listOf()),
//    )).filter { attributes.contains(it.key) }.filterNot { excludedAttributes.contains(it.key) }
//        .filterNot { it.key == "id" }.flatMap { attr ->
//            if (listOf("name").contains(attr.key)) listOf(attr.key to attr.value.first())
//            else attr.value.mapIndexed { index, value -> "${attr.key}[$index]" to value }
//        }.toMap() + mapOf("id" to this.id)
}
