package forum.service.user;

import forum.model.User;
import forum.repository.UserRepository;

import java.util.List;

/**
 * UserServiceAbs.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/17/2020
 */
public abstract class UserServiceAbs {
    /**
     * field a user repository.
     */
    private final UserRepository users;

    /**
     * Constructor.
     *
     * @param aUsers users repo
     */
    public UserServiceAbs(final UserRepository aUsers) {
        this.users = aUsers;
    }

    /**
     * Method to add user.
     *
     * @param user a user
     * @return new user or null
     */
    public User add(final User user) {
        return this.users.save(user);
    }

    /**
     * Method to get.
     *
     * @param id id by user
     * @return user or null
     */
    public final User get(final Integer id) {
        return this.users.findById((long) id).orElse(null);
    }

    /**
     * Method to update.
     *
     * @param user a user for update
     * @return update user or null
     */
    public User update(final User user) {
        return this.users.save(user);
    }

    /**
     * Method to delete user.
     *
     * @param user a user
     */
    public void delete(final User user) {
        this.users.delete(user);
    }

    /**
     * Method to get all user.
     *
     * @return list of user all users
     */
    public List<User> getAll() {
        return this.users.findAll();
    }

    /**
     * Method to get.
     *
     * @param name name by user
     * @return user or null
     */
    public User getByName(final String name) {
        return this.users.findByUsername(name);
    }

}
