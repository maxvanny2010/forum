package forum.service.message;

import forum.model.Message;
import forum.model.Post;
import forum.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

/**
 * PostMessageService.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/21/2020
 */
@Service
public class MessageService {
    private final MessageRepository message;
    public MessageService(final MessageRepository aMessages) {
        this.message = aMessages;
    }

    public final Message add(final Message message) {
        return this.message.save(message);
    }

    public final Message update(final Message message) {
        return this.message.save(message);
    }

    public final void delete(final Post post, final Message msg) {
        this.message.deleteByPostAndMsg(post, msg);
    }

    public final Message getById(final Long id) {
        return this.message.findById(id).orElse(null);
    }

    public final List<Message> getAll(final Post post) {
        return this.message.findByPost(post);
    }

    public final List<Message> getAll(final String userName, final Post post) {
        return this.message.findByAuthorAndPost(userName, post);
    }

    public final Message findByPostAndMsgId(final Post post, final Long id) {
        return this.message.findByPostAndId(post, id);
    }
    public void createMessage(@RequestParam("message") final String msg,
                              final String name,
                              final Post post) {
        final Message message = new Message();
        message.setDescription(msg.trim());
        message.setAuthor(name);
        message.setCreated(LocalDateTime.now());
        message.setPost(post);
        this.add(message);
    }
}
