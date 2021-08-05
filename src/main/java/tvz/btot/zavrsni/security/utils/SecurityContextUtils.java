package tvz.btot.zavrsni.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.service.impl.UserDetailsImpl;

public final class SecurityContextUtils {
    public static User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth == null ? User.GUEST_USER : ((UserDetailsImpl) auth.getPrincipal()).getUser();
    }

    private SecurityContextUtils() {
        throw new UnsupportedOperationException();
    }
}
