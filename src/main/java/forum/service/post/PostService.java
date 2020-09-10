package forum.service.post;

import forum.model.Post;
import forum.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * PostService.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/16/2020
 */
@Service
public class PostService {
    private final PostRepository posts;

    public PostService(final PostRepository aPosts) {
        this.posts = aPosts;
    }

    public final Post add(final Post post) {
        return this.posts.save(post);
    }

    public final Post update(final Post post) {
        return this.posts.save(post);
    }

    public final void delete(final Post post) {
        this.posts.delete(post);
    }

    public final Post findByNameAndId(final String userName, final Long id) {
        return this.posts.findByAuthorAndId(userName, id);
    }
    public final Optional<Post> findBydId(final Long id) {
        return this.posts.findById(id);
    }

    public final List<Post> getAll(final String userName) {
        return this.posts.findByAuthor(userName);
    }

    public final List<Post> getAll() {
        return this.posts.findAll();
    }

    public void createPost(@ModelAttribute final Post posts,
                           @RequestParam("date") final String date,
                           final String name) {
        final Post post = new Post();
        final LocalDateTime dateTime = date.isEmpty()
                ? LocalDateTime.now()
                : LocalDateTime.parse(date);
        post.setAuthor(name);
        post.setCreated(dateTime);
        post.setDescription(posts.getDescription());
        post.setMessages(new HashSet<>());
        post.setName(posts.getName());
        this.posts.save(post);
    }

    public void updatePost(@ModelAttribute final Post posts) {
        this.posts.findById(posts.getId()).ifPresent(post -> {
            post.setCreated(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
            post.setName(posts.getName());
            post.setDescription(posts.getDescription());
            this.posts.save(post);
        });
    }

}
