package forum.service.post;

import forum.model.Post;
import forum.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PostService.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/16/2020
 */
@Service
public class PostUserService implements PostService {
    /**
     * field a repo.
     */
    private final PostRepository posts;

    /**
     * Constructor.
     *
     * @param aPosts post
     */
    public PostUserService(final PostRepository aPosts) {
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
        return this.posts.findByUserNameAndId(userName, id);
    }

    @Override
    public final List<Post> getAll(final String userName) {
        return this.posts.findByUserName(userName);
    }
}
