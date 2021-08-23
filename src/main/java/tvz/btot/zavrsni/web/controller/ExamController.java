package tvz.btot.zavrsni.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tvz.btot.zavrsni.security.preauthorization.AllowAnonymous;
import tvz.btot.zavrsni.security.preauthorization.AllowStudent;
import tvz.btot.zavrsni.web.controller.base.CrudController;
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

    @PostMapping("/{examId}/submitAnswer")
    @AllowStudent
    public ResponseEntity<Void> submitAnswer(final @PathVariable Integer examId,
                                             final @RequestParam("questionId") Integer questionId,
                                             final @RequestParam("userId") Integer userId,
                                             final @RequestParam("answerId") Integer answerId) {
        examService.submitAnswer(examId, questionId, userId, answerId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/{examId}/terminate")
    @AllowStudent
    public ResponseEntity<Void> terminate(final @PathVariable Integer examId,
                                          final @RequestParam("userId") Integer userId) {
        examService.terminate(examId, userId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/{examId}/startExam")
    @AllowStudent
    public ResponseEntity<Void> startExam(final @PathVariable Integer examId,
                                          final @RequestParam("userId") Integer userId) {
        examService.startExam(examId, userId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @Override
    @GetMapping
    @AllowStudent
    public ResponseEntity<List<ExamDto>> findAll() {
        return ResponseEntity
                .ok(examService.findAll());
    }

    @Override
    @GetMapping("/{examId}")
    @AllowStudent
    public ResponseEntity<ExamDto> findById(final @PathVariable Integer examId) {
        return ResponseEntity
                .ok(examService.findById(examId));
    }

    @Override
    @PostMapping
    @AllowTeacher
    public ResponseEntity<ExamDto> create(final @RequestBody ExamForm examForm) {
        final ExamDto createdExamDto = examService.create(examForm);
        final String uri = String.format("/exam/%s", createdExamDto.getId());
        return ResponseEntity
                .created(URI.create(uri))
                .body(createdExamDto);
    }

    @GetMapping(params = "subjectId")
    @AllowStudent
    public ResponseEntity<List<ExamDto>> getAllBySubjectId(final @RequestParam Integer subjectId) {
        return ResponseEntity
                .ok(examService.findAllBySubjectId(subjectId));
    }

    @Override
    @GetMapping("/{examId}/form")
    @AllowTeacher
    public ResponseEntity<ExamForm> getFormById(final @PathVariable Integer examId) {
        return ResponseEntity
                .ok(examService.getForm(examId));
    }

    @Override
    @DeleteMapping("/{examId}")
    @AllowTeacher
    public ResponseEntity<Void> delete(final @PathVariable Integer examId) {
        examService.delete(examId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping("/{examId}")
    @AllowTeacher
    public ResponseEntity<ExamDto> update(final @PathVariable Integer examId,
                                          final @RequestBody ExamForm examForm) {
        return ResponseEntity
                .ok(examService.update(examId, examForm));
    }
}
