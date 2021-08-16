package tvz.btot.zavrsni.web.converter;

import org.springframework.stereotype.Component;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.web.converter.base.BaseConverter;
import tvz.btot.zavrsni.web.dto.ChatUserDto;
import tvz.btot.zavrsni.web.form.NoOpForm;

@Component
public class ChatUserConverter implements BaseConverter<User, NoOpForm, ChatUserDto> {
    @Override
    public ChatUserDto sourceToDto(final User source) {
        return source == null ? null : ChatUserDto.builder()
                .userId(source.getId())
                .userName(source.getUsername())
                .build();
    }
}
