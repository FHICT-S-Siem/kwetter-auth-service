package user;

import lombok.AllArgsConstructor;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@AllArgsConstructor
public class UserService {
    @Inject
    UserRepository userRepository;

    @Transactional
    public Boolean create(User user) {
        User.persist(user);
        return user.isPersistent();
    }
}
