package forum.service.user;

import forum.model.User;
import forum.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/17/2020
 */
@Service
public class UserService {
    private final UserRepository users;

    public UserService(final UserRepository aUsers) {
        this.users = aUsers;
    }

    public User add(final User user) {
        return this.users.save(user);
    }

    public final User get(final Long id) {
        return this.users.findById(id).orElse(null);
    }

    public User update(final User user) {
        return this.users.save(user);
    }

    public void delete(final User user) {
        this.users.delete(user);
    }

    public List<User> getAll() {
        return this.users.findAll();
    }

    public User getByName(final String name) {
        return this.users.findByUsername(name);
    }

}
