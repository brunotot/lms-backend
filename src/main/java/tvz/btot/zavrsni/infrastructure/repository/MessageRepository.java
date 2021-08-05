package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.Message;
import tvz.btot.zavrsni.web.form.MessageForm;

import java.util.List;

public interface MessageRepository {
    Message create(MessageForm messageForm);
    List<Message> findAllFromChat(Integer chatId);
}
