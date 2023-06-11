package user;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public void persist(User user) {
        PanacheRepository.super.persist(user);
    }

    @Override
    public User findById(Long id) {
        return PanacheRepository.super.findById(id);
    }

}
