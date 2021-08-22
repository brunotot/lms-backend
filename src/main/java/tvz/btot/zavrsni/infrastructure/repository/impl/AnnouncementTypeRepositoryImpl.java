package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import tvz.btot.zavrsni.domain.AnnouncementType;
import tvz.btot.zavrsni.infrastructure.dao.AnnouncementTypeDao;
import tvz.btot.zavrsni.infrastructure.repository.AnnouncementTypeRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Repository
public class AnnouncementTypeRepositoryImpl implements AnnouncementTypeRepository {
    private final AnnouncementTypeDao announcementTypeDao;

    public AnnouncementTypeRepositoryImpl(final AnnouncementTypeDao announcementTypeDao) {
        this.announcementTypeDao = announcementTypeDao;
    }

    @Override
    public List<AnnouncementType> getAll() {
        return announcementTypeDao.getAll(SqlQueryParams.newInstance());
    }
}
