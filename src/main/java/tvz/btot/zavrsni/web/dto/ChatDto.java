package tvz.btot.zavrsni.web.dto;

import lombok.*;
import tvz.btot.zavrsni.domain.ChatType;
import tvz.btot.zavrsni.domain.User;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {
    private Integer id;
    private String name;
    private String description;
    private Timestamp dateCreated;
    private Timestamp lastMessageDateCreated;
    private UserDto individualChatUser1;
    private UserDto individualChatUser2;
    private List<UserDto> multipleChatUsers;
    private ChatType type;
}
