package tvz.btot.zavrsni.web.dto;

import lombok.*;
import tvz.btot.zavrsni.domain.KeyValue;
import tvz.btot.zavrsni.domain.Role;

import java.util.List;

import static tvz.btot.zavrsni.infrastructure.utils.Constants.PASSWORD_ENCODER;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private KeyValue course;
    private String username;
    private String email;
    private boolean active;
    private List<Role> roles;
}
