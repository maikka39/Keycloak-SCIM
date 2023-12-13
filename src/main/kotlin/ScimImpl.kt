import extensions.toScimGroup
import extensions.toScimUser
import models.SearchBody
import models.scim.ScimGroup
import models.scim.ScimListResponse
import models.scim.ScimUser
import org.keycloak.models.KeycloakSession
import java.util.stream.Collectors

class ScimImpl(private val session: KeycloakSession) {
    fun getUser(id: String): ScimUser? {
        return session.users().getUserById(session.context.realm, id)
            ?.toScimUser(listOf("name", "emails", "groups", "roles"))
    }

    fun findUsers(searchBody: SearchBody): ScimListResponse {
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

        val userResources = users().skip(searchBody.startIndex).limit(searchBody.count)
            .map { it.toScimUser(searchBody.attributes, searchBody.excludedAttributes) }.collect(Collectors.toList())

        return ScimListResponse(users().count(), userResources.size.toLong(), searchBody.startIndex + 1, userResources)
    }

    fun getGroup(id: String): ScimGroup? {
        return session.groups().getGroupById(session.context.realm, id)?.toScimGroup(session)
    }

    fun findGroups(searchBody: SearchBody): ScimListResponse {
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

        val groupResources = groups().skip(searchBody.startIndex).limit(searchBody.count)
            .map { it.toScimGroup(session, searchBody.attributes, searchBody.excludedAttributes) }
            .collect(Collectors.toList())

        return ScimListResponse(
            groups().count(), groupResources.size.toLong(), searchBody.startIndex + 1, groupResources
        )
    }

}