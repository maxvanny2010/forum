package forum.control;

import forum.model.Message;
import forum.model.Post;
import forum.service.message.MessageService;
import forum.service.post.PostService;
import forum.service.user.util.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * PostController.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/17/2020
 */
@Controller
public class PostController {
    private final PostService posts;
    private final MessageService message;

    public PostController(final PostService aPost,
                          final MessageService aMessage) {
        this.posts = aPost;
        this.message = aMessage;
    }

    @PostMapping("/post/message/update")
    public String updateMessage(
            @RequestParam(value = "idMsgUpdate") final Long idMsg,
            @RequestParam(value = "idMsgPostUpdate") final Long idPost,
            @RequestParam(value = "authorPost") final String authorPost,
            @RequestParam(value = "msgUpdate") final String msg) {
        final String authorityName = Util.getAuthorityName();
        final boolean isAuthorPost = Objects.equals(authorPost, authorityName);
        final Post post = this.posts.findByNameAndId(authorPost, idPost);
        final Message message = this.message.findByPostAndMsgId(post, idMsg);
        if (!msg.isEmpty()) {
            message.setDescription(msg);
            message.setCreated(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
            this.message.update(message);
        }
        return isAuthorPost
                ? Util.getPathPost(idPost, authorPost, "show")
                : Util.getPathPost(idPost, authorPost, "shows");
    }

    @PostMapping("/post/message/delete")
    public String deleteMessage(
            @RequestParam(value = "idMsgD") final Long idMsg,
            @RequestParam(value = "idPostD") final Long id,
            @RequestParam(value = "authorPostD") final String authorPost) {
        final String name = Util.getAuthorityName();
        final boolean isAuthorPost = Objects.equals(authorPost, name);
        final Post post = this.posts.findByNameAndId(authorPost, id);
        final Message msg = this.message.findByPostAndMsgId(post, idMsg);
        if (Objects.nonNull(post) && Objects.nonNull(msg)) {
            this.message.delete(post, msg);
        }
        return isAuthorPost
                ? Util.getPathPost(id, name, "show")
                : Util.getPathPost(id, authorPost, "shows");
    }

    @PostMapping("/post/message/create")
    public String createMessage(@RequestParam(value = "id") final Long id,
                                @RequestParam(value = "message") final String msg,
                                @RequestParam(value = "authorPost") final String authorPost) {
        final String name = Util.getAuthorityName();
        final boolean isAuthorPost = Objects.equals(authorPost, name);
        final boolean isEmpty = msg.trim().isEmpty();
        if (isEmpty) {
            return isAuthorPost
                    ? Util.getPathPost(id, name, "show")
                    : Util.getPathPost(id, authorPost, "shows");
        }
        final Post post = this.posts.findByNameAndId(authorPost, id);
        this.message.createMessage(msg, name, post);
        return isAuthorPost ?
                Util.getPathPost(id, name, "show") :
                Util.getPathPost(id, authorPost, "shows");
    }

    @GetMapping("/404")
    public final String move404() {
        return "404";
    }

    @GetMapping("/post")
    public final String moveToCreate(
            @RequestParam(value = "id", required = false) final Post post,
            @RequestParam(value = "name", required = false) final String owner,
            @RequestParam(value = "action", required = false) final String action,
            final Model model) {
        final String name = Util.getAuthorityName();
        switch (action) {
            case "create":
                model.addAttribute("action", "create");
                model.addAttribute("name", name);
                return "edit";
            case "update":
                if (Objects.nonNull(post)) {
                    model.addAttribute("action", "update");
                    model.addAttribute("post", post);
                    model.addAttribute("name", name);
                    return "edit";
                }
                break;
            case "show":
                return getAllBy(owner, post, model) ? "post" : "redirect:/404";
            default:
                return getAllBy(name, post, model) ? "post" : "redirect:/404";
        }
        return "redirect:/404";
    }


    @PostMapping("/update")
    public final String update(@ModelAttribute final Post posts) {
        final String name = Util.getAuthorityName();
        this.posts.updatePost(posts);
        final String role = Objects.equals("admin", name) ? "admin" : "user";
        return Util.getPathCabinet(role, name);
    }


    @PostMapping("post/update")
    public final String update(@RequestParam(value = "idPost") final Long id) {
        final String name = Util.getAuthorityName();
        return Util.getPathPost(id, name, "update");
    }

    @PostMapping("/create")
    public final String create(@ModelAttribute final Post posts,
                               @RequestParam(value = "date") final String date) {
        final String name = Util.getAuthorityName();
        this.posts.createPost(posts, date, name);
        final String role = Objects.equals("admin", name) ? "admin" : "user";
        return Util.getPathCabinet(role, name);
    }


    @PostMapping("/remove")
    public final String remove(@RequestParam(value = "id") final Long id) {
        final String name = Util.getAuthorityName();
        final Post post = this.posts.findByNameAndId(name, id);
        this.posts.delete(post);
        final String role = Objects.equals("admin", name) ? "admin" : "user";
        return Util.getPathCabinet(role, name);
    }

    private boolean getAllBy(@RequestParam(value = "name", required = false) final String name,
                             final Post post, final Model model) {
        if (Objects.nonNull(post)) {
            final List<Message> msg = this.message.getAll(post);
            model.addAttribute("post", post);
            model.addAttribute("msg", msg);
            model.addAttribute("name", name);
            return true;
        }
        return false;
    }
}
