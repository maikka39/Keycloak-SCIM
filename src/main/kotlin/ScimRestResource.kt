
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import models.ListResponse
import models.OtherMediaType
import org.keycloak.models.KeycloakSession
import org.keycloak.services.resources.Cors
import java.util.regex.Pattern
import java.util.stream.Collectors
import kotlin.math.max

class ScimRestResource(private val session: KeycloakSession) {
    @OPTIONS
    @Path("{any:.*}")
    fun preflight(): Response {
        return Cors.add(session.context.httpRequest, Response.ok()).auth().preflight().build()
    }

    @GET
    @Path("Users")
    @Produces(OtherMediaType.APPLICATION_SCIM_JSON)
    fun getUsers(
        @DefaultValue("1") @QueryParam("startIndex") rawSkip: Long,
        @DefaultValue("100") @QueryParam("count") rawLimit: Long,
        @DefaultValue("userName") @QueryParam("attributes") rawAttributes: String,
        @DefaultValue("") @QueryParam("excludedAttributes") rawExcludedAttributes: String,
        @QueryParam("filter") rawFilter: String?,
    ): Response {
        val skip = max(1, rawSkip) - 1
        val limit = max(0, rawLimit)

        val attributesToReturn = rawAttributes.split(',')
        val attributesToNotReturn = rawExcludedAttributes.split(',')

//        /scim/v2/Users?filter=userName+eq+%227818078a-5d8c-4e4c-823c-0d3b1ed025b4%22
        val userAttributeFilters = rawFilter?.let {
            val regex = "(\\w+) eq \"([^\"]*)\"";
            val response = Pattern.compile(regex);
            val match = response.matcher(rawFilter)
            val found = match.find()

            if (found) match.group(1) to match.group(2) else null
        }

//        val rawParams = if (found) mapOf(match.group(1) to match.group(2)) else mapOf()
//        val params = rawParams.mapKeysNotNull { param ->
//            when (param.key) {
//                "userName" -> UserModel.USERNAME
//                "email" -> UserModel.EMAIL
//                "active" -> UserModel.ENABLED
//                else -> null
//            }
//        }


        val realm = session.context.realm
        val users = {
            userAttributeFilters?.let { attribute ->
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
            .skip(skip)
            .limit(limit)
            .map { user ->
                mapOf("id" to user.id) + user.attributes
                    .mapKeys {
                        when (it.key) {
                            "username" -> "userName"
                            else -> it.key
                        }
                    }
                    .filter { attributesToReturn.contains(it.key) }
                    .filterNot { attributesToNotReturn.contains(it.key) }
                    .filterNot { it.key == "id" }
                    .flatMap { attr ->
                        if (listOf("userName").contains(attr.key))
                            listOf(attr.key to attr.value.first())
                        else
                            attr.value.mapIndexed { index, value -> "${attr.key}[$index]" to value }
                    }
            }
            .collect(Collectors.toList())
            .toList()

        val data = ListResponse(users().count(), userResources.size.toLong(), skip + 1, userResources)

        return Response.status(200).entity(data).build()
    }

    @GET
    @Path("Users/{id}")
    @Produces(OtherMediaType.APPLICATION_SCIM_JSON)
    fun getUser(
        @PathParam("id") id: String
    ): Response {
        val user = session.users().getUserById(session.context.realm, id)

        return (user?.let { Response.status(200).entity(it.id) } ?: Response.status(404)).build()
    }
}