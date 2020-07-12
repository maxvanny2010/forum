package forum.control;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * LoginControl.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/17/2020
 */
@Controller
public class LoginControl {
    @GetMapping("/login")
    public String moveToLoginForm(
            @RequestParam(value = "error", required = false) final String error,
            @RequestParam(value = "logout", required = false) final String logout,
            final Model model) {
        String errorMessage = null;
        if (Objects.nonNull(error)) {
            errorMessage = "is incorrect";
        }
        if (Objects.nonNull(logout)) {
            errorMessage = " logout is success";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    /**
     * Method handler form logout.
     *
     * @param req  request
     * @param resp response
     * @return path for redirect
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public final String logoutPage(@RequestParam(value = "name") final String name,
                                   final HttpServletRequest req,
                                   final HttpServletResponse resp) {

        final Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();

        if (Objects.nonNull(auth) && Objects.equals(auth.getName(), name)) {
            new SecurityContextLogoutHandler().logout(req, resp, auth);
        }
        return "redirect:/login?logout=true";
    }
}
