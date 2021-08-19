package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.Chat;
import tvz.btot.zavrsni.web.form.ChatForm;

import java.util.List;

public interface ChatRepository {
    List<Chat> findExistingChats(Integer userId);
    Chat findById(Integer chatId);
    Chat create(ChatForm chatForm);
    Chat findIndividualChat(Integer userId1, Integer userId2);
}
