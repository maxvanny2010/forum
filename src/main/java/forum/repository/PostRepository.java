package forum.repository;

import forum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PostRepository.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/26/2020
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(String userName);

    Post findByAuthorAndId(String userName, Integer id);
}
