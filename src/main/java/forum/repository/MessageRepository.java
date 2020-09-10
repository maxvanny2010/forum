package forum.repository;

import forum.model.Message;
import forum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * MessageRepository.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/26/2020
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByPost(Post post);

    List<Message> findByAuthorAndPost(String userName, Post post);

    Message findByPostAndId(Post post, Long idMsg);

    @Transactional
    @Modifying
    @Query("DELETE FROM Message m WHERE m.post= ?1 and m.id =:#{#msg.id}")
    void deleteByPostAndMsg(Post post, @Param("msg") Message msg);
}
