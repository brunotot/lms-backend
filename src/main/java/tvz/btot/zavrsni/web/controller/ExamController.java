package tvz.btot.zavrsni.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tvz.btot.zavrsni.infrastructure.service.CrudController;
import tvz.btot.zavrsni.infrastructure.service.ExamService;
import tvz.btot.zavrsni.security.preauthorization.AllowAdmin;
import tvz.btot.zavrsni.security.preauthorization.AllowTeacher;
import tvz.btot.zavrsni.web.dto.ExamDto;
import tvz.btot.zavrsni.web.form.ExamForm;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController implements CrudController<ExamDto, ExamForm, Integer> {
    private final ExamService examService;

    public ExamController(final ExamService examService) {
        this.examService = examService;
    }

    @Override
    public ResponseEntity<List<ExamDto>> findAll() {
        // TODO
        return null;
    }

    @Override
    public ResponseEntity<ExamDto> findById(final Integer examId) {
        // TODO
        return null;
    }

    @Override
    @PostMapping
    @AllowAdmin
    @AllowTeacher
    public ResponseEntity<ExamDto> create(final @RequestBody ExamForm examForm) {
        final ExamDto createdExamDto = examService.create(examForm);
        final String uri = String.format("/exam/%s", createdExamDto.getId());
        return ResponseEntity
                .created(URI.create(uri))
                .body(createdExamDto);
    }

    @GetMapping(params = "subjectId")
    public ResponseEntity<List<ExamDto>> getAllBySubjectId(final @RequestParam Integer subjectId) {
        return ResponseEntity
                .ok(examService.findAllBySubjectId(subjectId));
    }

    @Override
    @GetMapping("/{examId}/form")
    public ResponseEntity<ExamForm> getFormById(final @PathVariable Integer examId) {
        return ResponseEntity
                .ok(examService.getForm(examId));
    }

    @Override
    @DeleteMapping("/{examId}")
    public ResponseEntity<Void> delete(final @PathVariable Integer examId) {
        examService.delete(examId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping("/{examId}")
    public ResponseEntity<ExamDto> update(final @PathVariable Integer examId,
                                          final @RequestBody ExamForm examForm) {
        return ResponseEntity
                .ok(examService.update(examId, examForm));
    }
}
