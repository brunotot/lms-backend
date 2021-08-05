package tvz.btot.zavrsni.web.converter;

import org.springframework.stereotype.Component;
import tvz.btot.zavrsni.domain.Message;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.utils.DateTimeUtils;
import tvz.btot.zavrsni.web.converter.base.BaseConverter;
import tvz.btot.zavrsni.web.dto.MessageDto;
import tvz.btot.zavrsni.web.form.MessageForm;

@Component
public class MessageConverter implements BaseConverter<Message, MessageForm, MessageDto> {
    private final ChatUserConverter chatUserConverter;

    public MessageConverter(final ChatUserConverter chatUserConverter) {
        this.chatUserConverter = chatUserConverter;
    }

    @Override
    public MessageDto sourceToDto(final Message source) {
        return source == null ? null : MessageDto.builder()
                .id(source.getId())
                .chatId(source.getChatId())
                .sender(chatUserConverter.sourceToDto(source.getSender()))
                .dateCreated(DateTimeUtils.convertTimestampToDateString(source.getDateCreated()))
                .text(source.getText())
                .build();
    }

    @Override
    public Message formToSource(final MessageForm form) {
        return form == null ? null : Message.builder()
                .id(form.getId())
                .chatId(form.getChatId())
                .sender(User.builder().id(form.getSenderId()).build())
                .dateCreated(form.getDateCreated())
                .text(form.getText())
                .build();
    }
}
