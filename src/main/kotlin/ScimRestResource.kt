import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import models.OtherMediaType
import models.SearchBody
import org.keycloak.models.KeycloakSession
import org.keycloak.services.resources.Cors
import kotlin.math.max

class ScimRestResource(private val session: KeycloakSession) {
    val scim = ScimImpl(session)

    @OPTIONS
    @Path("{any:.*}")
    fun preflight(): Response {
        return Cors.add(session.context.httpRequest, Response.ok()).auth().preflight().build()
    }

    @POST
    @Path(".search")
    @Consumes(OtherMediaType.APPLICATION_SCIM_JSON, MediaType.APPLICATION_JSON)
    @Produces(OtherMediaType.APPLICATION_SCIM_JSON)
    fun search(searchBody: SearchBody): Response {
        val users = scim.findUsers(searchBody)
        val groups = scim.findGroups(
            searchBody.copy(
                startIndex = max(searchBody.startIndex - users.totalResults, 0),
                count = searchBody.count - (users.totalResults - (users.startIndex - 1))
            )
        )
        return Response.status(200).entity(users + groups).build()
    }

    @GET
    @Path("Users")
    @Produces(OtherMediaType.APPLICATION_SCIM_JSON)
    fun getUsers(
        @QueryParam("startIndex") startIndex: Long?,
        @QueryParam("count") count: Long?,
        @QueryParam("attributes") attributes: String?,
        @QueryParam("excludedAttributes") excludedAttributes: String?,
        @QueryParam("filter") filter: String?,
    ): Response {
        val searchBody = SearchBody(startIndex, count, attributes, excludedAttributes, filter)
        val users = scim.findUsers(searchBody)
        return Response.status(200).entity(users).build()
    }

    @GET
    @Path("Users/{id}")
    @Produces(OtherMediaType.APPLICATION_SCIM_JSON)
    fun getUser(@PathParam("id") id: String): Response {
        val user = session.users().getUserById(session.context.realm, id)
        return (user?.let { Response.status(200).entity(it.id) } ?: Response.status(404)).build()
    }

    @GET
    @Path("Groups")
    @Produces(OtherMediaType.APPLICATION_SCIM_JSON)
    fun getGroups(
        @QueryParam("startIndex") startIndex: Long?,
        @QueryParam("count") count: Long?,
        @QueryParam("attributes") attributes: String?,
        @QueryParam("excludedAttributes") excludedAttributes: String?,
        @QueryParam("filter") filter: String?,
    ): Response {
        val searchBody = SearchBody(startIndex, count, attributes, excludedAttributes, filter)
        val groups = scim.findGroups(searchBody)
        return Response.status(200).entity(groups).build()
    }

    @GET
    @Path("Groups/{id}")
    @Produces(OtherMediaType.APPLICATION_SCIM_JSON)
    fun getGroup(@PathParam("id") id: String): Response {
        val group = session.groups().getGroupById(session.context.realm, id)
        return (group?.let { Response.status(200).entity(it.id) } ?: Response.status(404)).build()
    }
}