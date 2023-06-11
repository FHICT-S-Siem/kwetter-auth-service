package user;

import org.eclipse.microprofile.reactive.messaging.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.quarkus.logging.Log;

@Path("/test")
public class UserProducer {
    @Channel("user-channel")
    Emitter<User> emitter;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User user(User user) {
        // log
        Log.info(user.toString());

        // send user
        emitter.send(user);

        return user;
    }
}
