package forum.service.post;

import forum.model.Post;

import java.util.List;

/**
 * PostServiceAbs.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/17/2020
 */
public interface PostService {


    /**
     * Method to add post.
     *
     * @param post a post
     * @return new post or null
     */
    Post add(Post post);

    /**
     * Method to get by id.
     *
     * @param userName a name author of post
     * @param id       id by post
     * @return post or null
     */
    Post findByNameAndId(String userName, Integer id);

    /**
     * Method to update.
     *
     * @param post a post for update
     * @return update post or null
     */
    Post update(Post post);

    /**
     * Method to delete post.
     *
     * @param post a id of post
     */
    void delete(Post post);

    /**
     * Method to get all post by user.
     *
     * @param userName name of user
     * @return list of post by user
     */
    List<Post> getAll(String userName);
}
