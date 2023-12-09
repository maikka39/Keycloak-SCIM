import models.ListResponse
import models.SearchBody
import org.keycloak.models.KeycloakSession
import java.util.stream.Collectors

class ScimImpl(private val session: KeycloakSession) {
    fun findUsers(searchBody: SearchBody): ListResponse {
//        /scim/v2/Users?filter=userName+eq+%227818078a-5d8c-4e4c-823c-0d3b1ed025b4%22
        val realm = session.context.realm
        val users = {
            searchBody.filter?.let { attribute ->
                when (attribute.first) {
                    "userName" -> listOf(session.users().getUserByUsername(realm, attribute.second)).stream()
                    "email" -> listOf(session.users().getUserByEmail(realm, attribute.second)).stream()
                    else -> {
                        session.users().searchForUserByUserAttributeStream(realm, attribute.first, attribute.second)
                    }
                }
            } ?: session.users().searchForUserStream(realm, mapOf())
        }
        val userResources = users()
            .skip(searchBody.startIndex)
            .limit(searchBody.count)
            .map { user ->
                user.attributes
                    .mapKeys {
                        when (it.key) {
                            "username" -> "userName"
                            else -> it.key
                        }
                    }
                    .filter { searchBody.attributes.contains(it.key) }
                    .filterNot { searchBody.excludedAttributes.contains(it.key) }.filterNot { it.key == "id" }
                    .flatMap { attr ->
                        if (listOf("userName").contains(attr.key)) listOf(attr.key to attr.value.first())
                        else attr.value.mapIndexed { index, value -> "${attr.key}[$index]" to value }
                    }
                    .toMap() + mapOf("id" to user.id)
            }
            .collect(Collectors.toList()).toList()

        return ListResponse(users().count(), userResources.size.toLong(), searchBody.startIndex + 1, userResources)
    }

    fun findGroups(searchBody: SearchBody): ListResponse {
        val realm = session.context.realm
        val groups = {
            searchBody.filter?.let { attribute ->
                when (attribute.first) {
                    "name", "displayName" -> listOf(
                        session.groups().getGroupByName(realm, null, attribute.second)
                    ).stream()

                    else -> {
                        session.groups().searchGroupsByAttributes(realm, mapOf(attribute), null, null)
                    }
                }
            } ?: session.groups().getGroupsStream(realm)
        }
        val groupResources = groups()
            .skip(searchBody.startIndex)
            .limit(searchBody.count)
            .map { group ->
                (group.attributes + mapOf("name" to listOf(group.name)))
                    .filter { searchBody.attributes.contains(it.key) }
                    .filterNot { searchBody.excludedAttributes.contains(it.key) }.filterNot { it.key == "id" }
                    .flatMap { attr ->
                        if (listOf("name").contains(attr.key)) listOf(attr.key to attr.value.first())
                        else attr.value.mapIndexed { index, value -> "${attr.key}[$index]" to value }
                    }
                    .toMap() + mapOf("id" to group.id)
            }
            .collect(Collectors.toList()).toList()

        return ListResponse(groups().count(), groupResources.size.toLong(), searchBody.startIndex + 1, groupResources)
    }

}