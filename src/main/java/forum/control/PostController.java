package forum.control;

import forum.model.Message;
import forum.model.Post;
import forum.service.message.MessageService;
import forum.service.message.PostMessageService;
import forum.service.post.PostService;
import forum.service.post.PostUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
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
    private final PostService postsUser;
    private final MessageService message;

    public PostController(final PostUserService aPost,
                          final PostMessageService aMessage) {
        this.postsUser = aPost;
        this.message = aMessage;
    }

    @PostMapping("/post/message/update")
    public String updateMessage(
            @RequestParam(value = "idMsgUpdate") final Integer idMsg,
            @RequestParam(value = "idMsgPostUpdate") final Integer idPost,
            @RequestParam(value = "authorPost") final String authorPost,
            @RequestParam(value = "authorMsg") final String authorMsg,
            @RequestParam(value = "msgUpdate") final String msg) {
        final String authorityName = this.getAuthorityName();
        final boolean isName = Objects.equals(authorMsg, authorityName);
        final boolean isAuthorPost = Objects.equals(authorPost, authorityName);
        final boolean isAdmin = !Objects.equals("admin", authorMsg);
        final Post post = this.postsUser.findByNameAndId(authorPost, idPost);
        final Message message = this.message.findByPostAndMsgId(post, idMsg);
        if (!msg.isEmpty()) {
            message.setDescription(msg);
            message.setCreated(LocalDateTime.of(LocalDate.now(), LocalTime.NOON));
            this.message.update(message);
        }
        if (isAuthorPost && (isAdmin || isName)) {
            return this.getPathPost(idPost, authorPost, "show");
        }
        return this.getPathPost(idPost, authorPost, "shows");
    }

    @PostMapping("/post/message/delete")
    public String deleteMessage(
            @RequestParam(value = "idMsgD") final Integer idMsg,
            @RequestParam(value = "idPostD") final Integer idPost,
            @RequestParam(value = "namesD") final String name,
            @RequestParam(value = "authorPostD") final String authorPost) {
        final String authorityName = this.getAuthorityName();
        final boolean isName = Objects.equals(name, authorityName);
        final boolean isAdmin = Objects.equals("admin", name);
        final boolean isAuthorPost = Objects.equals(authorPost, authorityName);
        final Post post = this.postsUser.findByNameAndId(authorPost, idPost);
        final Message msg = this.message.findByPostAndMsgId(post, idMsg);
        if (Objects.nonNull(post) && Objects.nonNull(msg)) {
            this.message.delete(post, msg);
        }
        if (isAuthorPost && (isAdmin || isName)) {
            return this.getPathPost(idPost, authorPost, "show");
        }
        return this.getPathPost(idPost, authorPost, "shows");
    }

    @PostMapping("/post/message/create")
    public String createMessage(@RequestParam(value = "id") final Integer id,
                                @RequestParam(value = "message") final String msg,
                                @RequestParam(value = "authorPost") final String authorPost) {
        final String authorityName = this.getAuthorityName();
        final boolean isAdmin = Objects.equals("admin", authorPost);
        final boolean isAuthorPost = Objects.equals(authorPost, authorityName);
        final boolean isEmpty = msg.trim().isEmpty();
        if (isEmpty) {
            return isAuthorPost
                    ? this.getPathPost(id, authorPost, "show")
                    : this.getPathPost(id, authorPost, "shows");
        }
        final Post post = this.postsUser.findByNameAndId(authorPost, id);
        final Message message = new Message();
        message.setDescription(msg.trim());
        message.setAuthor(authorityName);
        message.setCreated(LocalDateTime.now());
        message.setPost(post);
        this.message.add(message);
        if (isAuthorPost && !isAdmin) {
            return this.getPathPost(id, authorityName, "show");
        }
        return this.getPathPost(id, authorPost, "shows");
    }

    @GetMapping("/404")
    public final String move404() {
        return "404";
    }

    @GetMapping("/post")
    public final String moveToCreate(
            @RequestParam(value = "id", required = false) final Integer id,
            @RequestParam(value = "name", required = false) final String name,
            @RequestParam(value = "action", required = false) final String action,
            final Model model) {
        final String authorityName = this.getAuthorityName();
        final boolean isSame = Objects.equals(authorityName, name);
        final boolean isAdmin = Objects.equals("admin", authorityName);
        Post post;
        switch (action) {
            case "create":
                if (isSame || isAdmin) {
                    model.addAttribute("action", "create");
                    model.addAttribute("name", authorityName);
                    return "edit";
                }
                break;
            case "update":
                if (isSame || isAdmin) {
                    post = this.postsUser.findByNameAndId(name, id);
                    if (Objects.nonNull(post)) {
                        model.addAttribute("action", "update");
                        model.addAttribute("post", post);
                        model.addAttribute("name", authorityName);
                        return "edit";
                    }
                }
                break;
            case "show":
                if (isSame) {
                    post = this.postsUser.findByNameAndId(name, id);
                    if (getAllBy(name, model, post)) {
                        return "post";
                    }
                }
                break;
            default:
                post = this.postsUser.findByNameAndId(name, id);
                if (getAllBy(authorityName, model, post)) {
                    return "post";
                }
                break;
        }
        return "redirect:/404";
    }

    private boolean getAllBy(@RequestParam(value = "name", required = false) final String name,
                             final Model model, final Post post) {
        if (Objects.nonNull(post)) {
            final List<Message> msg = this.message.getAll(post);
            model.addAttribute("post", post);
            model.addAttribute("msg", msg);
            model.addAttribute("name", name);
            return true;
        }
        return false;
    }

    @PostMapping("/update")
    public final String update(@ModelAttribute final Post posts,
                               @RequestParam(value = "authorPost") final String authorPost,
                               @RequestParam(value = "date") final String date) {
        final String name = this.getAuthorityName();
        final boolean isAdmin = Objects.equals("admin", name);
        if (this.isNames(authorPost, name) || isAdmin) {
            final Post post = this.postsUser.findByNameAndId(authorPost, posts.getId());
            if (!date.isEmpty()) {
                post.setCreated(LocalDateTime.parse(date));
            }
            post.setName(posts.getName());
            post.setDescription(posts.getDescription());
            this.postsUser.update(post);
            final String role = Objects.equals("admin", name) ? "admin" : "user";
            return this.getPathCabinet(role, name);
        }
        return "redirect:/404";
    }

    @PostMapping("post/update")
    public final String update(@RequestParam(value = "idPost") final Integer idPost,
                               @RequestParam(value = "authorPostUpdate") final String authorPost) {
        final String name = this.getAuthorityName();
        final boolean isAdmin = Objects.equals("admin", name);
        if (this.isNames(authorPost, name) || isAdmin) {
            return this.getPathPost(idPost, authorPost, "update");
        }
        return "redirect:/404";
    }

    @PostMapping("/create")
    public final String create(@ModelAttribute final Post posts,
                               @RequestParam(value = "names") final String names,
                               @RequestParam(value = "date") final String date) {
        final String name = this.getAuthorityName();
        if (this.isNames(names, name)) {
            final Post post = new Post();
            final LocalDateTime dateTime = date.isEmpty()
                    ? LocalDateTime.now()
                    : LocalDateTime.parse(date);
            post.setAuthor(name);
            post.setCreated(dateTime);
            post.setDescription(posts.getDescription());
            post.setMessages(new HashSet<>());
            post.setName(posts.getName());
            this.postsUser.add(post);
            final String role = Objects.equals("admin", name) ? "admin" : "user";
            return this.getPathCabinet(role, name);
        }
        return "redirect:/404";
    }

    @PostMapping("/remove")
    public final String remove(@RequestParam(value = "id") final Integer id,
                               @RequestParam(value = "postAuthor") final String authorPost) {
        final String name = this.getAuthorityName();
        final boolean isAdmin = Objects.equals("admin", name);
        if (this.isNames(authorPost, name) || isAdmin) {
            final Post post = this.postsUser.findByNameAndId(authorPost, id);
            this.postsUser.delete(post);
            final String role = Objects.equals("admin", name) ? "admin" : "user";
            return this.getPathCabinet(role, name);
        }
        return "redirect:/404";
    }

    private String getPathCabinet(final String role, final String name) {
        return String.format("redirect:/cabinet/%s?name=%s", role, name);
    }

    private String getPathPost(final Integer id, final String name, final String action) {
        return String.format("redirect:/post?action=%s&id=%d&name=%s", action, id, name);
    }

    private boolean isNames(final String names, final String name) {
        return Objects.equals(name, names) && Objects.nonNull(names);
    }

    private String getAuthorityName() {
        final SecurityContext context = SecurityContextHolder.getContext();
        final Authentication auth = context.getAuthentication();
        return auth.getName();
    }
}
