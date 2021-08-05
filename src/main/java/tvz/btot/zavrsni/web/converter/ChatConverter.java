package tvz.btot.zavrsni.web.converter;

import org.springframework.stereotype.Component;
import tvz.btot.zavrsni.domain.Chat;
import tvz.btot.zavrsni.infrastructure.utils.DateTimeUtils;
import tvz.btot.zavrsni.web.converter.base.BaseConverter;
import tvz.btot.zavrsni.web.dto.ChatDto;
import tvz.btot.zavrsni.web.form.ChatForm;

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
                .name(source.getName())
                .type(source.getType())
                .description(source.getDescription())
                .dateCreated(DateTimeUtils.convertTimestampToDateString(source.getDateCreated()))
                .individualChatUser1(userConverter.sourceToDto(source.getIndividualChatUser1()))
                .individualChatUser2(userConverter.sourceToDto(source.getIndividualChatUser2()))
                .multipleChatUsers(userConverter.sourceToDtoList(source.getMultipleChatUsers()))
                .build();
    }
}
