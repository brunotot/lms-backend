package tvz.btot.zavrsni.web.form;

import lombok.*;
import tvz.btot.zavrsni.web.dto.UserDto;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatForm {
    private Integer id;
    private String name;
    private String description;
    private Timestamp dateCreated;
    private Integer userId1;
    private Integer userId2;
    private List<Integer> multipleUserIds;
}
