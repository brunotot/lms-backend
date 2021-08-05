package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import tvz.btot.zavrsni.domain.Message;
import tvz.btot.zavrsni.infrastructure.dao.MessageDao;
import tvz.btot.zavrsni.infrastructure.repository.MessageRepository;
import tvz.btot.zavrsni.infrastructure.repository.UserRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;
import tvz.btot.zavrsni.web.converter.MessageConverter;
import tvz.btot.zavrsni.web.form.MessageForm;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    private final MessageDao messageDao;
    private final MessageConverter messageConverter;
    private final UserRepository userRepository;

    public MessageRepositoryImpl(final MessageDao messageDao,
                                 final MessageConverter messageConverter,
                                 final UserRepository userRepository) {
        this.messageDao = messageDao;
        this.messageConverter = messageConverter;
        this.userRepository = userRepository;
    }

    @Override
    public Message create(final MessageForm messageForm) {
        Message message = messageConverter.formToSource(messageForm);
        SqlQueryParams params = SqlQueryParams.newInstance(message);
        messageDao.create(params);
        message.setId(params.getOutputParam("newMessageId", Integer.class));
        message.setDateCreated(params.getOutputParam("dateCreated", Timestamp.class));
        message.setSender(userRepository.findById(messageForm.getSenderId()));
        return message;
    }

    @Override
    public List<Message> findAllFromChat(final Integer chatId) {
        return messageDao.findAllFromChat(SqlQueryParams.newInstance("chatId", chatId));
    }
}
