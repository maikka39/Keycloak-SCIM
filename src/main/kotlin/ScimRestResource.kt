import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import org.keycloak.models.KeycloakSession
import org.keycloak.models.utils.ModelToRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.keycloak.services.managers.AppAuthManager.BearerTokenAuthenticator
import org.keycloak.services.managers.AuthenticationManager.AuthResult
import org.keycloak.services.resources.Cors
import org.keycloak.utils.MediaType
import java.util.stream.Collectors

//@Path("v2")
class ScimRestResource(private val session: KeycloakSession) {
    private val auth: AuthResult? = BearerTokenAuthenticator(session).authenticate()

    @OPTIONS
    @Path("{any:.*}")
    fun preflight(): Response {
        return Cors.add(session.context.httpRequest, Response.ok()).auth().preflight().build()
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUsersByAttr(
        @QueryParam("key") attrKey: String,
        @QueryParam("value") attrValue: String,
        @DefaultValue("100") @QueryParam("maxResults") maxResults: Int
    ): Response {
        checkRealmAccess()
        val data: List<UserRepresentation> = session.users()
            .searchForUserByUserAttributeStream(session.context.realm, attrKey, attrValue)
            .map { userModel ->
                ModelToRepresentation.toRepresentation(
                    session,
                    session.context.realm,
                    userModel
                )
            }
            .collect(Collectors.toList())
        return Response.status(200)
            .header("Access-Control-Allow-Origin", "*")
            .entity(data)
            .build()
    }

    private fun checkRealmAccess() {
//        if (auth == null) {
//            throw NotAuthorizedException("Bearer")
//        } else if (auth.token.realmAccess == null || !auth.token.realmAccess.isUserInRole("fetch_users")) {
//            throw ForbiddenException("Does not have permission to fetch users")
//        }
    }
}