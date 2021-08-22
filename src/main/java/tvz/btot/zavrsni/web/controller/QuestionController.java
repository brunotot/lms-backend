package tvz.btot.zavrsni.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tvz.btot.zavrsni.infrastructure.service.QuestionService;
import tvz.btot.zavrsni.security.preauthorization.AllowAdmin;
import tvz.btot.zavrsni.security.preauthorization.AllowStudent;
import tvz.btot.zavrsni.security.preauthorization.AllowTeacher;
import tvz.btot.zavrsni.web.controller.base.CrudController;
import tvz.btot.zavrsni.web.dto.QuestionDto;
import tvz.btot.zavrsni.web.form.QuestionForm;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController implements CrudController<QuestionDto, QuestionForm, Integer> {
    private final QuestionService questionService;

    public QuestionController(final QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    @GetMapping
    @AllowAdmin
    @AllowTeacher
    @AllowStudent
    public ResponseEntity<List<QuestionDto>> findAll() {
        return ResponseEntity
                .ok(questionService.findAll());
    }

    @Override
    @GetMapping("/{questionId}")
    @AllowAdmin
    @AllowTeacher
    @AllowStudent
    public ResponseEntity<QuestionDto> findById(final @PathVariable Integer questionId) {
        return ResponseEntity
                .ok(questionService.findById(questionId));
    }

    @Override
    @PostMapping
    @AllowAdmin
    @AllowTeacher
    public ResponseEntity<QuestionDto> create(final @RequestBody QuestionForm questionForm) {
        QuestionDto questionDto = questionService.create(questionForm);
        String uriString = String.format("/question/%s", questionDto.getId());
        return ResponseEntity
                .created(URI.create(uriString))
                .body(questionDto);
    }

    @Override
    @PutMapping("/{questionId}")
    @AllowAdmin
    @AllowTeacher
    public ResponseEntity<QuestionDto> update(final @PathVariable Integer questionId,
                                              final @RequestBody QuestionForm questionForm) {
        return ResponseEntity
                .ok(questionService.update(questionId, questionForm));
    }

    @Override
    @GetMapping("/{questionId}/form")
    @AllowAdmin
    @AllowTeacher
    public ResponseEntity<QuestionForm> getFormById(final @PathVariable Integer questionId) {
        return ResponseEntity
                .ok(questionService.getFormById(questionId));
    }

    @Override
    @AllowTeacher
    @AllowAdmin
    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> delete(final @PathVariable Integer questionId) {
        questionService.delete(questionId);
        return ResponseEntity
                .noContent()
                .build();
    }
}