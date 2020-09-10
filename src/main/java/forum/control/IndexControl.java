package forum.control;

import forum.service.post.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * IndexControl.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/16/2020
 */
@Controller
public class IndexControl {
    private final PostService posts;

    public IndexControl(final PostService aPost) {
        this.posts = aPost;
    }

    @GetMapping({"/", "/forum"})
    public String index(final Model model) {
        final Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        model.addAttribute("name", auth.getName());
        model.addAttribute("posts", this.posts.getAll());
        return "forum";
    }
}

