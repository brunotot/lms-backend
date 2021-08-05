package tvz.btot.zavrsni.domain;

import lombok.*;
import tvz.btot.zavrsni.security.utils.SecurityContextUtils;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    private Integer id;
    private ChatType type;
    private String name;
    private String description;
    private Timestamp dateCreated;
    private User individualChatUser1;
    private User individualChatUser2;
    private List<User> multipleChatUsers;

    public String getName() {
        Integer thisUserId = SecurityContextUtils.getUser().getId();
        if (ChatType.INDIVIDUAL.equals(type)) {
            String user1Username = individualChatUser1.getUsername();
            String user2Username = individualChatUser2.getUsername();
            boolean isOwnerUser1 = thisUserId.equals(individualChatUser1.getId());
            boolean isOwnerUser2 = thisUserId.equals(individualChatUser2.getId());
            if (!isOwnerUser1 && !isOwnerUser2) {
                return String.format("%s & %s", user1Username, user2Username);
            }
            return isOwnerUser1 ? user2Username : user1Username;
        }
        return this.name;
    }

    public List<User> getChatPeopleExcludingRequestOwner() {
        final Integer thisUserId = SecurityContextUtils.getUser().getId();
        if (ChatType.INDIVIDUAL.equals(type)) {
            return Stream.of(individualChatUser1, individualChatUser2)
                    .filter(user -> !thisUserId.equals(user.getId()))
                    .collect(Collectors.toList());
        }
        return Optional.ofNullable(multipleChatUsers)
                .orElse(Collections.emptyList())
                .stream()
                .filter(user -> !thisUserId.equals(user.getId()))
                .collect(Collectors.toList());
    }

    public List<User> getChatPeopleIncludingRequestOwner() {
        if (ChatType.INDIVIDUAL.equals(type)) {
            return List.of(individualChatUser1, individualChatUser2);
        }
        return multipleChatUsers;
    }
}
