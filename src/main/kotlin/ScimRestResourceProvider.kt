import org.keycloak.models.KeycloakSession
import org.keycloak.services.resource.RealmResourceProvider

class ScimRestResourceProvider(private val session: KeycloakSession) : RealmResourceProvider {
    override fun getResource(): Any {
        return ScimRestResource(session)
    }

    override fun close() {}
}