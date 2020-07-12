package forum.control;

import forum.service.post.PostAdminService;
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
    /**
     * field a service post.
     */
    private final PostAdminService posts;

    /**
     * Constructor.
     *
     * @param aService service
     */
    public IndexControl(final PostAdminService aService) {
        this.posts = aService;
    }

    /**
     * Method to get all posts.
     *
     * @param model model
     * @return index page
     */
    @GetMapping({"/", "/forum"})
    public String index(final Model model) {
        final Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        model.addAttribute("name", auth.getName());
        model.addAttribute("posts", this.posts.getAll());
        return "forum";
    }
}

