package tvz.btot.zavrsni.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtPayloadDto {
    private String token;
    private UserDto user;
}
