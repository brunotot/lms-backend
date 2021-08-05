package tvz.btot.zavrsni.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tvz.btot.zavrsni.infrastructure.service.ExamService;
import tvz.btot.zavrsni.infrastructure.utils.JsonObject;
import tvz.btot.zavrsni.web.dto.ExamDto;
import tvz.btot.zavrsni.web.form.ExamForm;

import java.util.List;

@RestController
@RequestMapping("/subject/{subjectId}/exam")
public class ExamController {
    private final ExamService examService;

    public ExamController(final ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ExamDto createExam(final @RequestBody ExamForm examForm,
                              final @PathVariable Integer subjectId) {
        return examService.create(examForm);
    }

    @GetMapping
    public List<ExamDto> getAllBySubjectId(final @PathVariable Integer subjectId) {
        return examService.findAllBySubjectId(subjectId);
    }

    @GetMapping("/{examId}/form")
    public ExamForm getForm(final @PathVariable Integer subjectId,
                            final @PathVariable Integer examId) {
        ExamForm form = examService.getForm(examId);
        form.setSubjectId(subjectId);
        return form;
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<Void> deleteExam(final @PathVariable Integer subjectId,
                                        final @PathVariable Integer examId) {
        examService.delete(examId);
        return ResponseEntity.ok().build();
    }
}
