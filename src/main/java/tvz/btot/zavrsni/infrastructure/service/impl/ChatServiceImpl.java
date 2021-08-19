package tvz.btot.zavrsni.infrastructure.service.impl;

import org.springframework.stereotype.Service;
import tvz.btot.zavrsni.infrastructure.repository.ChatRepository;
import tvz.btot.zavrsni.infrastructure.repository.SubjectRepository;
import tvz.btot.zavrsni.infrastructure.service.ChatService;
import tvz.btot.zavrsni.infrastructure.service.SubjectService;
import tvz.btot.zavrsni.web.converter.ChatConverter;
import tvz.btot.zavrsni.web.converter.SubjectConverter;
import tvz.btot.zavrsni.web.dto.ChatDto;
import tvz.btot.zavrsni.web.dto.SubjectDto;
import tvz.btot.zavrsni.web.form.ChatForm;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final ChatConverter chatConverter;

    public ChatServiceImpl(final ChatRepository chatRepository, final ChatConverter chatConverter) {
        this.chatRepository = chatRepository;
        this.chatConverter = chatConverter;
    }

    @Override
    public List<ChatDto> findExistingChats(final Integer userId) {
        return chatConverter.sourceToDtoList(chatRepository.findExistingChats(userId));
    }

    @Override
    public ChatDto findById(final Integer chatId) {
        return chatConverter.sourceToDto(chatRepository.findById(chatId));
    }

    @Override
    public ChatDto create(final ChatForm chatForm) {
        return chatConverter.sourceToDto(chatRepository.create(chatForm));
    }
}
