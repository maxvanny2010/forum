package forum.control;

import forum.service.post.PostService;
import forum.service.user.util.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

/**
 * CabinetControl.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/18/2020
 */
@Controller
public class CabinetControl {
    private final PostService posts;

    public CabinetControl(final PostService aPosts) {
        this.posts = aPosts;
    }

    @GetMapping("/cabinet/user")
    public String cabinetUser(@RequestParam(value = "name") final String name,
                              final Model model) {
        final String authorityName = Util.getAuthorityName();
        if (Objects.equals(authorityName, name)) {
            model.addAttribute("posts", this.posts.getAll(name));
            model.addAttribute("name", name);
            return "cabinet";
        }
        return "redirect:/404";
    }

    @GetMapping("/cabinet/admin")
    public String cabinetAdmin(@RequestParam(value = "name") final String name,
                               final Model model) {
        final String authorityName = Util.getAuthorityName();
        final boolean isName = Objects.equals(authorityName, name);
        final boolean isAdmin = Objects.equals("admin", name);
        if (isName && isAdmin) {
            model.addAttribute("posts", this.posts.getAll());
            model.addAttribute("name", name);
            return "cabinet";
        }
        return "redirect:/404";
    }
}
