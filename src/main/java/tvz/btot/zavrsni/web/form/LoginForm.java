package tvz.btot.zavrsni.web.form;

import lombok.*;

import static tvz.btot.zavrsni.infrastructure.utils.Constants.PASSWORD_ENCODER;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginForm {
    private String username;
    private String password;

    public String getEncryptedPassword() {
        return PASSWORD_ENCODER.encode(password);
    }
}
