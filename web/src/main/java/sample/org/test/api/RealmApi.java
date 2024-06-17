package sample.org.test.api;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class RealmApi {

    @GET
    @Path("/public")
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    public String publicEndpoint() {
        return "Public endpoint";
    }

    @GET
    @Path("/private")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("admin")
    public String privateEndpoint() {
        return "Private endpoint";
    }
}
