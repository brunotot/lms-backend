package tvz.btot.zavrsni.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tvz.btot.zavrsni.infrastructure.service.AnnouncementService;
import tvz.btot.zavrsni.web.controller.base.CrudController;
import tvz.btot.zavrsni.security.preauthorization.AllowAdmin;
import tvz.btot.zavrsni.security.preauthorization.AllowStudent;
import tvz.btot.zavrsni.security.preauthorization.AllowTeacher;
import tvz.btot.zavrsni.web.dto.AnnouncementDto;
import tvz.btot.zavrsni.web.form.AnnouncementForm;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController implements CrudController<AnnouncementDto, AnnouncementForm, Integer> {
    private final AnnouncementService announcementService;

    public AnnouncementController(final AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/{subjectId}/active")
    @AllowStudent
    @AllowTeacher
    @AllowAdmin
    public ResponseEntity<List<AnnouncementDto>> findAllActiveAnnouncementsBySubjectId(final @PathVariable Integer subjectId) {
        return ResponseEntity
                .ok(announcementService.findActiveAnnouncementsBySubjectId(subjectId));
    }

    @Override
    @GetMapping
    @AllowStudent
    @AllowTeacher
    @AllowAdmin
    public ResponseEntity<List<AnnouncementDto>> findAll() {
        return ResponseEntity
                .ok(announcementService.findAll());
    }

    @Override
    @GetMapping("/{announcementId}")
    @AllowStudent
    @AllowTeacher
    @AllowAdmin
    public ResponseEntity<AnnouncementDto> findById(final @PathVariable Integer announcementId) {
        return ResponseEntity
                .ok(announcementService.findById(announcementId));
    }

    @Override
    @PostMapping
    @AllowTeacher
    @AllowAdmin
    public ResponseEntity<AnnouncementDto> create(final @RequestBody AnnouncementForm announcementForm) {
        AnnouncementDto dto = announcementService.create(announcementForm);
        String uriString = String.format("/announcement/%s", dto.getId());
        return ResponseEntity
                .created(URI.create(uriString))
                .body(dto);
    }

    @Override
    @PutMapping("/{announcementId}")
    @AllowTeacher
    @AllowAdmin
    public ResponseEntity<AnnouncementDto> update(final @PathVariable Integer announcementId,
                                                  final @RequestBody AnnouncementForm announcementForm) {
        return ResponseEntity
                .ok(announcementService.update(announcementId, announcementForm));
    }

    @Override
    @GetMapping("/{announcementId}/form")
    @AllowTeacher
    @AllowAdmin
    public ResponseEntity<AnnouncementForm> getFormById(final @PathVariable Integer announcementId) {
        return ResponseEntity
                .ok(announcementService.getFormById(announcementId));
    }

    @Override
    @DeleteMapping("/{announcementId}")
    @AllowTeacher
    @AllowAdmin
    public ResponseEntity<Void> delete(final @PathVariable Integer announcementId) {
        announcementService.delete(announcementId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
