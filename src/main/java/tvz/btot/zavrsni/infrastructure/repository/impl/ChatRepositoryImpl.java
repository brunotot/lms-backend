package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import tvz.btot.zavrsni.domain.Chat;
import tvz.btot.zavrsni.domain.Subject;
import tvz.btot.zavrsni.infrastructure.dao.ChatDao;
import tvz.btot.zavrsni.infrastructure.dao.SubjectDao;
import tvz.btot.zavrsni.infrastructure.repository.ChatRepository;
import tvz.btot.zavrsni.infrastructure.repository.SubjectRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Repository
public class ChatRepositoryImpl implements ChatRepository {
    private final ChatDao chatDao;

    public ChatRepositoryImpl(final ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    @Override
    public List<Chat> findExistingChats(Integer userId) {
        return chatDao.findExistingChats(SqlQueryParams.newInstance("userId", userId));
    }
}
