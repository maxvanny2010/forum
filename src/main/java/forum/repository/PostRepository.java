package forum.repository;

import forum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    @Query("SELECT p FROM Post p WHERE p.author= ?1")
    List<Post> findByUserName(String userName);

    @Query("SELECT p FROM Post p WHERE p.author= ?1 AND p.id= ?2")
    Post findByUserNameAndId(String userName, Integer id);
}
