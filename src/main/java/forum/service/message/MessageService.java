package forum.service.message;

import forum.model.Message;
import forum.model.Post;

import java.util.List;

/**
 * MessageService.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/21/2020
 */
public interface MessageService {
    /**
     * Method to add.
     *
     * @param message a message
     * @return a new message
     */
    Message add(Message message);

    /**
     * Method to get by id.
     *
     * @param id a id
     * @return message
     */
    Message getById(Integer id);

    /**
     * Method to delete.
     *
     * @param post a post
     * @param msg  a message
     */
    void delete(Post post, Message msg);

    /**
     * Method to update.
     *
     * @param message a message
     * @return a new message
     */
    Message update(Message message);

    /**
     * Method to get.
     *
     * @param post a id of post
     * @return all messages
     */

    List<Message> getAll(Post post);

    /**
     * Method to get.
     *
     * @param userName a id of post
     * @param post     a id of post
     * @return all messages
     */
    List<Message> getAll(String userName, Post post);

    /**
     * Method to get.
     *
     * @param post a id of post
     * @param id   a id of message
     * @return message
     */
    Message findByPostAndMsgId(Post post, Integer id);
}
