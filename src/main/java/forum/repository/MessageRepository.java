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
    @Query("select m FROM Message m WHERE m.post= ?1")
    List<Message> findByPost(Post post);

    @Query("SELECT m FROM Message m WHERE m.author= ?1 AND m.post= ?2")
    List<Message> findByUserNameAndPost(String userName, Post post);

    @Query("FROM Message m WHERE m.post= ?1 AND m.id= ?2")
    Message findByPostAndMsgId(Post post, Integer idMsg);

    @Transactional
    @Modifying
    @Query("DELETE FROM Message m WHERE m.post= ?1 and m.id =:#{#msg.id}")
    void deleteByPostAndMsg(Post post, @Param("msg") Message msg);
}
