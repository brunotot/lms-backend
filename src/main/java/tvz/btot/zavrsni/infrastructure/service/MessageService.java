package tvz.btot.zavrsni.infrastructure.service;

import tvz.btot.zavrsni.domain.Message;
import tvz.btot.zavrsni.web.dto.MessageDto;
import tvz.btot.zavrsni.web.form.MessageForm;

import java.util.List;

public interface MessageService {
    MessageDto create(MessageForm messageForm);
    List<MessageDto> findAllFromChat(Integer chatId);
}
