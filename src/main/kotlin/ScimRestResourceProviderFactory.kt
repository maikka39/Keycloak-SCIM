import org.keycloak.Config
import org.keycloak.models.KeycloakSession
import org.keycloak.models.KeycloakSessionFactory
import org.keycloak.services.resource.RealmResourceProvider
import org.keycloak.services.resource.RealmResourceProviderFactory

class ScimRestResourceProviderFactory : RealmResourceProviderFactory {
    override fun getId(): String {
        return ID
    }

    override fun create(session: KeycloakSession): RealmResourceProvider {
        return ScimRestResourceProvider(session)
    }

    override fun init(config: Config.Scope) {}
    override fun postInit(factory: KeycloakSessionFactory) {}
    override fun close() {}

    companion object {
        const val ID = "scim"
    }
}