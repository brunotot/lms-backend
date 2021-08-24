package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import tvz.btot.zavrsni.domain.Announcement;
import tvz.btot.zavrsni.infrastructure.dao.AnnouncementDao;
import tvz.btot.zavrsni.infrastructure.repository.AnnouncementRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;
import tvz.btot.zavrsni.web.converter.AnnouncementConverter;
import tvz.btot.zavrsni.web.form.AnnouncementForm;

import java.util.List;

@Repository
public class AnnouncementRepositoryImpl implements AnnouncementRepository {
    private final AnnouncementDao announcementDao;
    private final AnnouncementConverter announcementConverter;

    public AnnouncementRepositoryImpl(final AnnouncementDao announcementDao,
                                      final AnnouncementConverter announcementConverter) {
        this.announcementDao = announcementDao;
        this.announcementConverter = announcementConverter;
    }

    @Override
    public List<Announcement> findAll() {
        return announcementDao.findAll(SqlQueryParams.newInstance());
    }

    @Override
    public Announcement findById(final Integer announcementId) {
        return announcementDao.findById(SqlQueryParams.newInstance("announcementId", announcementId));
    }

    @Override
    public Announcement create(final AnnouncementForm form) {
        Announcement announcement = announcementConverter.formToSource(form);
        SqlQueryParams params = SqlQueryParams.newInstance(announcement);
        announcementDao.create(params);
        announcement.setId(params.getOutputParam("newAnnouncementId", Integer.class));
        return announcement;
    }

    @Override
    public Announcement update(final Integer announcementId,
                               final AnnouncementForm announcementForm) {
        Announcement announcement = announcementConverter.formToSource(announcementForm);
        announcement.setId(announcementId);
        announcementDao.update(SqlQueryParams.newInstance(announcement));
        return announcement;
    }

    @Override
    public void delete(final Integer announcementId) {
        announcementDao.delete(SqlQueryParams.newInstance("announcementId", announcementId));
    }

    @Override
    public List<Announcement> findActiveAnnouncementsBySubjectId(final Integer subjectId) {
        return announcementDao.findActiveAnnouncementsBySubjectId(SqlQueryParams.newInstance("subjectId", subjectId));
    }
}