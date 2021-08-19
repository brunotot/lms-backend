package tvz.btot.zavrsni.infrastructure.service;

import tvz.btot.zavrsni.web.dto.ChatDto;
import tvz.btot.zavrsni.web.form.ChatForm;

import java.util.List;

public interface ChatService {
    List<ChatDto> findExistingChats(Integer userId);
    ChatDto findById(Integer chatId);
    ChatDto create(ChatForm chatForm);
}
