package user;

import lombok.AllArgsConstructor;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/api/v1/auth")
@AllArgsConstructor
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    @Inject
    UserProducer userProducer;
    @Inject
    UserService userService;

    @GET
    @PermitAll
    public Response getUserData() {
        List<User> users = User.listAll();
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        return User.findByIdOptional(id)
                .map(user -> Response.ok(user).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(User user) {
        Boolean isCreated = userService.create(user);
        if (isCreated){
            // Send user to RabbitMQ channel "user-channel"
            userProducer.user(user);
            return Response.created(URI.create("/auth" + user.id)).build();

        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") long id) {
        boolean deleted = User.deleteById(id);
        if(deleted){
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
