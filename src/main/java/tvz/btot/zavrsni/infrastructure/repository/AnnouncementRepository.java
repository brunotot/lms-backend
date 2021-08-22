package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.Announcement;
import tvz.btot.zavrsni.web.form.AnnouncementForm;

import java.util.List;

public interface AnnouncementRepository {
    List<Announcement> findAll();
    Announcement findById(Integer announcementId);
    Announcement create(AnnouncementForm form);
    Announcement update(Integer announcementId, AnnouncementForm announcementForm);
    void delete(Integer announcementId);
    List<Announcement> findActiveAnnouncementsBySubjectId(Integer subjectId);
}
