package tvz.btot.zavrsni.infrastructure.service;

import tvz.btot.zavrsni.domain.Announcement;
import tvz.btot.zavrsni.web.dto.AnnouncementDto;
import tvz.btot.zavrsni.web.form.AnnouncementForm;

import java.util.List;

public interface AnnouncementService {
    List<AnnouncementDto> findAll();
    List<AnnouncementDto> findActiveAnnouncementsBySubjectId(Integer subjectId);
    AnnouncementDto findById(Integer announcementId);
    AnnouncementForm getFormById(Integer announcementId);
    AnnouncementDto create(AnnouncementForm form);
    AnnouncementDto update(Integer announcementId, AnnouncementForm announcementForm);
    void delete(Integer announcementId);
}
