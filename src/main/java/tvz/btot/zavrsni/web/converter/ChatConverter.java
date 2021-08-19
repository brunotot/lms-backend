package tvz.btot.zavrsni.web.converter;

import org.springframework.stereotype.Component;
import tvz.btot.zavrsni.domain.Chat;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.utils.DateTimeUtils;
import tvz.btot.zavrsni.web.converter.base.BaseConverter;
import tvz.btot.zavrsni.web.dto.ChatDto;
import tvz.btot.zavrsni.web.form.ChatForm;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ChatConverter implements BaseConverter<Chat, ChatForm, ChatDto> {
    private final UserConverter userConverter;

    public ChatConverter(final UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public ChatDto sourceToDto(final Chat source) {
        return source == null ? null : ChatDto.builder()
                .id(source.getId())
                .type(source.getType())
                .name(source.getName())
                .description(source.getDescription())
                .dateCreated(source.getDateCreated())
                .lastMessageDateCreated(source.getLastMessageDateCreated())
                .individualChatUser1(userConverter.sourceToDto(source.getIndividualChatUser1()))
                .individualChatUser2(userConverter.sourceToDto(source.getIndividualChatUser2()))
                .multipleChatUsers(userConverter.sourceToDtoList(source.getMultipleChatUsers()))
                .build();
    }

    @Override
    public Chat formToSource(final ChatForm form) {
        return form == null ? null : Chat.builder()
                .id(form.getId())
                .type(form.getType())
                .name(form.getName())
                .description(form.getDescription())
                .dateCreated(form.getDateCreated())
                .individualChatUser1(User.builder().id(form.getUserId1()).build())
                .individualChatUser2(User.builder().id(form.getUserId2()).build())
                .multipleChatUsers(chatFormToUserList(form.getMultipleUserIds()))
                .build();
    }

    private List<User> chatFormToUserList(final List<Integer> multipleChatUsers) {
        return Optional.ofNullable(multipleChatUsers)
                .orElse(List.of())
                .stream()
                .map(id -> User.builder().id(id).build())
                .collect(Collectors.toList());
    }
}
