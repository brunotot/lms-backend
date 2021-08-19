package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import tvz.btot.zavrsni.domain.Chat;
import tvz.btot.zavrsni.domain.ChatType;
import tvz.btot.zavrsni.domain.Subject;
import tvz.btot.zavrsni.infrastructure.dao.ChatDao;
import tvz.btot.zavrsni.infrastructure.dao.SubjectDao;
import tvz.btot.zavrsni.infrastructure.repository.ChatRepository;
import tvz.btot.zavrsni.infrastructure.repository.SubjectRepository;
import tvz.btot.zavrsni.infrastructure.repository.UserRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;
import tvz.btot.zavrsni.security.utils.SecurityContextUtils;
import tvz.btot.zavrsni.web.converter.ChatConverter;
import tvz.btot.zavrsni.web.form.ChatForm;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class ChatRepositoryImpl implements ChatRepository {
    private final ChatDao chatDao;
    private final ChatConverter chatConverter;
    private final UserRepository userRepository;

    public ChatRepositoryImpl(final ChatDao chatDao,
                              final ChatConverter chatConverter,
                              final UserRepository userRepository) {
        this.chatDao = chatDao;
        this.chatConverter = chatConverter;
        this.userRepository = userRepository;
    }

    @Override
    public List<Chat> findExistingChats(final Integer userId) {
        return chatDao.findExistingChats(SqlQueryParams.newInstance("userId", userId));
    }

    @Override
    public Chat findById(final Integer chatId) {
        return chatDao.findById(SqlQueryParams.newInstance("chatId", chatId));
    }

    @Override
    public Chat findIndividualChat(final Integer userId1, final Integer userId2) {
        return chatDao.findIndividualChat(SqlQueryParams.newInstance()
                .param("userId1", userId1)
                .param("userId2", userId2));
    }

    @Override
    public Chat create(final ChatForm chatForm) {
        final Chat chat = chatConverter.formToSource(chatForm);
        boolean isIndividualChat = ChatType.INDIVIDUAL.equals(chat.getType());
        if (isIndividualChat) {
            Chat existingChat = this.findIndividualChat(chat.getIndividualChatUser1().getId(), chat.getIndividualChatUser2().getId());
            if (existingChat != null) {
                return existingChat;
            }
        }
        SqlQueryParams params = SqlQueryParams
                .newInstance(chat)
                .param("typeId", chat.getType().ordinal() + 1);
        chatDao.create(params);
        chat.setId(params.getOutputParam("newChatId", Integer.class));
        chat.setDateCreated(params.getOutputParam("dateCreated", Timestamp.class));
        chat.setIndividualChatUser1(userRepository.findById(chatForm.getUserId1()));
        if (isIndividualChat) {
            chat.setIndividualChatUser2(userRepository.findById(chatForm.getUserId2()));
        }
        chat.setMultipleChatUsers(chatForm.getMultipleUserIds()
                .stream()
                .map(userRepository::findById)
                .collect(Collectors.toList())
        );
        return chat;
    }
}
