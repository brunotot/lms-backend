package tvz.btot.zavrsni.infrastructure.service.impl;

import org.springframework.stereotype.Service;
import tvz.btot.zavrsni.domain.Announcement;
import tvz.btot.zavrsni.infrastructure.repository.AnnouncementRepository;
import tvz.btot.zavrsni.infrastructure.service.AnnouncementService;
import tvz.btot.zavrsni.infrastructure.service.ChatService;
import tvz.btot.zavrsni.web.converter.AnnouncementConverter;
import tvz.btot.zavrsni.web.dto.AnnouncementDto;
import tvz.btot.zavrsni.web.form.AnnouncementForm;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementConverter announcementConverter;

    public AnnouncementServiceImpl(final AnnouncementRepository announcementRepository,
                                   final AnnouncementConverter announcementConverter) {
        this.announcementRepository = announcementRepository;
        this.announcementConverter = announcementConverter;
    }

    @Override
    public List<AnnouncementDto> findAll() {
        return announcementConverter.sourceToDtoList(announcementRepository.findAll());
    }

    @Override
    public List<AnnouncementDto> findActiveAnnouncementsBySubjectId(final Integer subjectId) {
        return announcementConverter.sourceToDtoList(announcementRepository.findActiveAnnouncementsBySubjectId(subjectId));
    }

    @Override
    public AnnouncementDto findById(final Integer announcementId) {
        return announcementConverter.sourceToDto(announcementRepository.findById(announcementId));
    }

    @Override
    public AnnouncementForm getFormById(final Integer announcementId) {
        return announcementConverter.sourceToForm(announcementRepository.findById(announcementId));
    }

    @Override
    public AnnouncementDto create(final AnnouncementForm form) {
        return announcementConverter.sourceToDto(announcementRepository.create(form));
    }

    @Override
    public AnnouncementDto update(final Integer announcementId, final AnnouncementForm announcementForm) {
        return announcementConverter.sourceToDto(announcementRepository.update(announcementId, announcementForm));
    }

    @Override
    public void delete(final Integer announcementId) {
        announcementRepository.delete(announcementId);
    }
}
