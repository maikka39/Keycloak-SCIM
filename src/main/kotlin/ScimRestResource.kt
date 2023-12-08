import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import models.ListResponse
import models.ListResponseResource
import models.OtherMediaType
import org.keycloak.models.KeycloakSession
import org.keycloak.services.resources.Cors
import java.util.regex.Pattern
import java.util.stream.Collectors

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
        @DefaultValue("") @QueryParam("filter") filterString: String,
        @DefaultValue("0") @QueryParam("skip") skip: Long,
        @DefaultValue("100") @QueryParam("limit") limit: Long,
    ): Response {
        val regex = "(\\w+) eq \"([^\"]*)\"";
        val response = Pattern.compile(regex);
        val match = response.matcher(filterString)
        val found = match.find()

        val attributes = if (found) match.group(1) to match.group(2) else null

//        /scim/v2/Users?filter=userName+eq+%227818078a-5d8c-4e4c-823c-0d3b1ed025b4%22

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
            attributes?.let { attribute ->

                when (attribute.first) {
                    "userName" -> listOf(session.users().getUserByUsername(realm, attribute.second)).stream()
                    "email" -> listOf(session.users().getUserByEmail(realm, attribute.second)).stream()
                    else -> {
                        session.users().searchForUserByUserAttributeStream(realm, attribute.first, attribute.second)
                    }
                }

            } ?: session.users().searchForUserStream(realm, mapOf())
        }

        val userResources = users().skip(skip).limit(limit).map { ListResponseResource(it.id, it.username) }
            .collect(Collectors.toList())

        val data = ListResponse(users().count(), skip, userResources.size.toLong(), userResources)

        return Response.status(200).entity(data).build()
    }

    @GET
    @Path("Users/{id}")
    @Produces(OtherMediaType.APPLICATION_SCIM_JSON)
    fun getUser(
        @PathParam("id") id: String
    ): Response {
        return Response.status(200).entity(session.users().getUserById(session.context.realm, id)).build()
    }
}