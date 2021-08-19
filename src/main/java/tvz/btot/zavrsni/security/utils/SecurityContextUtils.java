package tvz.btot.zavrsni.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.service.impl.UserDetailsImpl;
import tvz.btot.zavrsni.security.jwt.JwtTokenProvider;

public final class SecurityContextUtils {
    public static String SOCKET_TOKEN = "";

    public static User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            try {
                return JwtTokenProvider.getUser(SOCKET_TOKEN);
            } catch (final Exception ignored) {
                return User.GUEST_USER;
            }
        }
        return ((UserDetailsImpl) auth.getPrincipal()).getUser();
    }

    private SecurityContextUtils() {
        throw new UnsupportedOperationException();
    }
}
