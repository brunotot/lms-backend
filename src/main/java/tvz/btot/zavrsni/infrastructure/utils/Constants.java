package tvz.btot.zavrsni.infrastructure.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class Constants {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private Constants() {
        throw new UnsupportedOperationException();
    }
}
