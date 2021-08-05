package tvz.btot.zavrsni.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Role implements GrantedAuthority {
    SUPERADMIN("SUPERADMIN"),
    ADMIN("ADMIN"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT"),
    GUEST("GUEST");

    private final String authority;

    Role(final String authority) {
        this.authority = authority;
    }

    public static List<SimpleGrantedAuthority> convertListRoleToListAuthority(final List<Role> roles) {
        return Optional.ofNullable(roles)
                .orElse(Collections.emptyList())
                .stream()
                .map(s -> new SimpleGrantedAuthority(s.getAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
