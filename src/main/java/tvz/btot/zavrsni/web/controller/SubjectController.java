package tvz.btot.zavrsni.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tvz.btot.zavrsni.web.controller.base.CrudController;
import tvz.btot.zavrsni.infrastructure.service.SubjectService;
import tvz.btot.zavrsni.security.preauthorization.AllowAnonymous;
import tvz.btot.zavrsni.web.dto.SubjectDto;
import tvz.btot.zavrsni.web.form.SubjectForm;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController implements CrudController<SubjectDto, SubjectForm, Integer> {
    private final SubjectService subjectService;

    public SubjectController(final SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(params = "courseId")
    public ResponseEntity<List<SubjectDto>> getAllByCourseId(@RequestParam("courseId") Integer courseId) {
        return ResponseEntity
                .ok(subjectService.getAllByCourseId(courseId));
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<SubjectDto>> getAllByUserId(@RequestParam("userId") Integer userId) {
        return ResponseEntity
                .ok(subjectService.getAllByUserId(userId));
    }

    @Override
    @GetMapping
    @AllowAnonymous
    public ResponseEntity<List<SubjectDto>> findAll() {
        return ResponseEntity
                .ok(subjectService.findAll());
    }

    @Override
    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectDto> findById(@PathVariable Integer subjectId) {
        return ResponseEntity
                .ok(subjectService.findById(subjectId));
    }

    @Override
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> delete(@PathVariable Integer subjectId) {
        subjectService.delete(subjectId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @Override
    @PostMapping
    public ResponseEntity<SubjectDto> create(@RequestBody SubjectForm form) {
        final SubjectDto createdSubjectDto = subjectService.create(form);
        final String uri = String.format("/subject/%s", createdSubjectDto.getId());
        return ResponseEntity
                .created(URI.create(uri))
                .body(createdSubjectDto);
    }

    @Override
    @PutMapping("/{subjectId}")
    public ResponseEntity<SubjectDto> update(final @PathVariable Integer subjectId,
                                             final @RequestBody SubjectForm subjectForm) {
        return ResponseEntity
                .ok(subjectService.update(subjectId, subjectForm));
    }

    @Override
    @GetMapping("/{subjectId}/form")
    public ResponseEntity<SubjectForm> getFormById(final @PathVariable Integer subjectId) {
        return ResponseEntity
                .ok(subjectService.getFormById(subjectId));
    }
}
