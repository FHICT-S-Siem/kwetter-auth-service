package user;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
@Path("/api/v1/role")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    List<User> users = new ArrayList<>();
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return Response.ok(users).build();
    }

    @POST
    @RolesAllowed({"moderator", "admin"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User newUser) {
        users.add(newUser);
        return Response.ok(users).build();
    }

    @Path("{id}")
    @RolesAllowed({"moderator", "admin"})
    @DELETE
    public Response deleteUser(@PathParam("id") Long id) {
        users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .ifPresent(user -> users.remove(user));
        return Response.noContent().build();
    }


}
