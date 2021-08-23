package tvz.btot.zavrsni.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.errorhandling.ApiException;
import tvz.btot.zavrsni.infrastructure.service.impl.UserDetailsImpl;
import tvz.btot.zavrsni.security.jwt.JwtTokenProvider;

public final class SecurityContextUtils {
    public static String SOCKET_TOKEN = "";

    public static void setUser(final String token, final JwtTokenProvider jwtTokenProvider) {
        try {
            SecurityContextUtils.SOCKET_TOKEN = token;
            jwtTokenProvider.validateToken(token);
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (final ApiException ex) {
            SecurityContextHolder.clearContext();
        }
    }

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
