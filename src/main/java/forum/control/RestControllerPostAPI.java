package forum.control;

import forum.model.Post;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * ResFullController.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/10/2020
 */
@Controller
@RequestMapping("/api")
public class RestControllerPostAPI {
    private static final String API = "http://bloomberg.com/post/";
    private static final String API_ID = "http://bloomberg.com/post/{id}";
    private final RestTemplate rest = new RestTemplate();

    @GetMapping("/")
    public List<Post> getAll() {
        return this.rest.exchange(API, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Post>>() {
                }).getBody();
    }

    @GetMapping("/post?{id}")
    public Post getById(@PathVariable final Integer id) {
        return this.rest.getForObject(API_ID, Post.class, id);
    }

    @PostMapping("/post")
    public Post create(@RequestBody final Post post) {
        if (this.getById(post.getId()) != null) {
            return this.rest.postForObject(API, post, Post.class);
        }
        return null;
    }

    @PutMapping("/post")
    public Post update(@RequestBody final Post post) {
        if (this.getById(post.getId()) != null) {
            this.rest.put(API, post);
        }
        return null;
    }

    @DeleteMapping("/post?{id}")
    public boolean delete(@PathVariable final Integer id) {
        if (this.getById(id) != null) {
            this.rest.delete(API_ID, id);
        }
        return Objects.isNull(this.getById(id));
    }
}
