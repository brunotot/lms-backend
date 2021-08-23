package tvz.btot.zavrsni.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tvz.btot.zavrsni.domain.AnnouncementType;
import tvz.btot.zavrsni.domain.Role;
import tvz.btot.zavrsni.infrastructure.service.AnnouncementTypeService;
import tvz.btot.zavrsni.infrastructure.service.RoleService;
import tvz.btot.zavrsni.security.preauthorization.AllowAnonymous;
import tvz.btot.zavrsni.security.preauthorization.AllowStudent;

import java.util.List;

@RestController
@RequestMapping("/announcement-type")
public class AnnouncementTypeController {
    private final AnnouncementTypeService announcementTypeService;

    public AnnouncementTypeController(final AnnouncementTypeService announcementTypeService) {
        this.announcementTypeService = announcementTypeService;
    }

    @GetMapping
    @AllowAnonymous
    public ResponseEntity<List<AnnouncementType>> getAll() {
        return ResponseEntity
            .ok(announcementTypeService.getAll());
    }
}
