package forum.control;

import forum.repository.AuthorityRepository;
import forum.service.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

/**
 * RegistrationControl.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/17/2020
 */
@Controller
public class RegistrationControl {
    /**
     * field a user.
     */
    private final UserService user;
    private final AuthorityRepository authority;
    /**
     * field a encoder.
     */
    private final PasswordEncoder encoder;

    public RegistrationControl(final UserService aUser,
                               final AuthorityRepository authority,
                               final PasswordEncoder aEncoder) {
        this.user = aUser;
        this.authority = authority;
        this.encoder = aEncoder;
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute final forum.model.User user) {
        final forum.model.User byName = this.user.getByName(user.getUsername());
        if (Objects.isNull(byName)) {
            user.setEnable(true);
            user.setAuthority(this.authority.findByAuthority("ROLE_USER"));
            user.setPassword(this.encoder.encode(user.getPassword()));
            this.user.add(user);
        } else {
            return "redirect:/registration?msg=false";
        }
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public final String moveToRegistration(@RequestParam(value = "msg", required = false) final String msg,
                                           final Model model) {
        if (Objects.nonNull(msg)) {
            model.addAttribute("msg", "user is exist");
        }
        return "registration";
    }
}
