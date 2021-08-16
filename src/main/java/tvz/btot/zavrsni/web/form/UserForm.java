package tvz.btot.zavrsni.web.form;

import lombok.*;
import tvz.btot.zavrsni.domain.Role;
import tvz.btot.zavrsni.infrastructure.utils.Constants;

import java.util.List;

import static tvz.btot.zavrsni.infrastructure.utils.Constants.PASSWORD_ENCODER;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private boolean active;
    private List<Role> roles;
    private Integer courseId;

    public String getEncryptedPassword() {
        return PASSWORD_ENCODER.encode(password);
    }
}
