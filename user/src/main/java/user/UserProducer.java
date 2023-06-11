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
        // test setting test User
//        User user = new User();
//        user.setUserState("Unblocked");
//        user.setName("siem");
//        user.setEmail("siem@gmail.com");
//        user.setNickname("siempie");
//        user.setSub("sub");
//        user.setPicture("pic");
//        user.setUpdated_at("2020 ofz");

        // log
        Log.info(user.toString());

        // send user
        emitter.send(user);

        return user;
    }
}
