package forum.control;

import forum.service.post.PostAdminService;
import forum.service.post.PostService;
import forum.service.post.PostUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * CabinetControl.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/18/2020
 */
@Controller
public class CabinetControl {
    /**
     * field a service user.
     */
    private final PostService postUser;
    /**
     * field a service admin.
     */
    private final PostAdminService postAdmin;

    /**
     * Constructor.
     *
     * @param aPostsAdmin admin
     * @param aPostsUser  user
     */
    public CabinetControl(final PostUserService aPostsUser,
                          final PostAdminService aPostsAdmin) {
        this.postUser = aPostsUser;
        this.postAdmin = aPostsAdmin;
    }

    /**
     * Method  to get.
     *
     * @param name  a name
     * @param model a model
     * @return page
     */
    @GetMapping("/cabinet/user")
    public String cabinetUser(@RequestParam(value = "name") final String name,
                              final Model model) {
        model.addAttribute("posts", this.postUser.getAll(name));
        model.addAttribute("name", name);
        return "cabinet";
    }

    /**
     * Method  to get.
     *
     * @param name  a name
     * @param model a model
     * @return page
     */
    @GetMapping("/cabinet/admin")
    public String cabinet(@RequestParam(value = "name") final String name,
                          final Model model) {
        model.addAttribute("posts", this.postAdmin.getAll());
        model.addAttribute("name", name);
        return "cabinet";
    }
}
