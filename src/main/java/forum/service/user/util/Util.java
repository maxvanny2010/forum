package forum.service.user.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Util.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/10/2020
 */
public final class Util {

    private Util() {
    }

    public static String getPathCabinet(final String role, final String name) {
        return String.format("redirect:/cabinet/%s?name=%s", role, name);
    }

    public static String getPathPost(final Long id, final String name, final String action) {
        return String.format("redirect:/post?action=%s&id=%d&name=%s", action, id, name);
    }

    public static String getAuthorityName() {
        final SecurityContext context = SecurityContextHolder.getContext();
        final Authentication auth = context.getAuthentication();
        return auth.getName();
    }

}
