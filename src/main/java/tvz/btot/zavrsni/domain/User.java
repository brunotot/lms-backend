package tvz.btot.zavrsni.domain;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static tvz.btot.zavrsni.infrastructure.utils.Constants.PASSWORD_ENCODER;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private static final Integer DEFAULT_ID = -1;
    private static final String DEFAULT_USERNAME = "guest";
    private static final String DEFAULT_PASSWORD = "guest";
    private static final String DEFAULT_ENCRYPTED_PASSWORD = PASSWORD_ENCODER.encode(DEFAULT_PASSWORD);
    private static final String DEFAULT_EMAIL = String.format("%s@tvz.hr", DEFAULT_USERNAME);
    private static final Integer DEFAULT_ACTIVE = 1;
    private static final List<Role> DEFAULT_ROLES = Collections.singletonList(Role.GUEST);
    public static final User GUEST_USER = User.builder()
            .id(DEFAULT_ID)
            .username(DEFAULT_USERNAME)
            .encryptedPassword(DEFAULT_ENCRYPTED_PASSWORD)
            .email(DEFAULT_EMAIL)
            .roles(DEFAULT_ROLES)
            .active(DEFAULT_ACTIVE)
            .build();

    private Integer id;
    private String username;
    private String encryptedPassword;
    private String email;
    private KeyValue course;
    private Integer active;
    private List<Role> roles;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return Role.convertListRoleToListAuthority(roles);
    }

    public String getRolesQuery() {
        return Optional.ofNullable(roles)
                .orElse(List.of())
                .stream()
                .map(r -> String.valueOf(r.ordinal() + 1))
                .collect(Collectors.joining(","));
    }
}
