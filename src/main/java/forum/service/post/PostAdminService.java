package forum.service.post;

import forum.model.Post;
import forum.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PostAdminService.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/17/2020
 */
@Service
public class PostAdminService implements PostService {
    /**
     * field a repo.
     */
    private final PostRepository posts;

    /**
     * Constructor.
     *
     * @param aPosts post
     */
    public PostAdminService(final PostRepository aPosts) {
        this.posts = aPosts;
    }

    @Override
    public final Post add(final Post post) {
        return this.posts.save(post);
    }

    @Override
    public final Post update(final Post post) {
        return this.posts.save(post);
    }

    @Override
    public final void delete(final Post post) {
        this.posts.delete(post);
    }

    @Override
    public final Post findByNameAndId(final String userName, final Integer id) {
        return this.posts.findByAuthorAndId(userName, id);
    }

    @Override
    public final List<Post> getAll(final String userName) {
        return this.posts.findByAuthor(userName);
    }

    /**
     * Method to get all post.
     *
     * @return list of posts all users
     */
    public final List<Post> getAll() {
        return this.posts.findAll();
    }
}
