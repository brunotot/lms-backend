package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.Chat;
import tvz.btot.zavrsni.web.dto.ChatDto;

import java.util.List;

public interface ChatRepository {
    List<Chat> findExistingChats(Integer userId);
}
