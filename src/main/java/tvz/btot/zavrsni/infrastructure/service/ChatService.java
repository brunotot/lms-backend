package tvz.btot.zavrsni.infrastructure.service;

import tvz.btot.zavrsni.web.dto.ChatDto;

import java.util.List;

public interface ChatService {
    List<ChatDto> findExistingChats(Integer userId);
    ChatDto findById(Integer chatId);
}
