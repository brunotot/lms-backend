package tvz.btot.zavrsni.infrastructure.service.impl;

import org.springframework.stereotype.Service;
import tvz.btot.zavrsni.domain.Message;
import tvz.btot.zavrsni.infrastructure.repository.MessageRepository;
import tvz.btot.zavrsni.infrastructure.service.MessageService;
import tvz.btot.zavrsni.web.converter.MessageConverter;
import tvz.btot.zavrsni.web.dto.MessageDto;
import tvz.btot.zavrsni.web.form.MessageForm;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageConverter messageConverter;

    public MessageServiceImpl(final MessageRepository messageRepository,
                              final MessageConverter messageConverter) {
        this.messageRepository = messageRepository;
        this.messageConverter = messageConverter;
    }

    @Override
    public MessageDto create(final MessageForm messageForm) {
        Message createdMessage = messageRepository.create(messageForm);
        return messageConverter.sourceToDto(createdMessage);
    }

    @Override
    public List<MessageDto> findAllFromChat(final Integer chatId) {
        return messageConverter.sourceToDtoList(messageRepository.findAllFromChat(chatId));
    }
}
