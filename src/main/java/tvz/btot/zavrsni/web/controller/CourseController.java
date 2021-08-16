package tvz.btot.zavrsni.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tvz.btot.zavrsni.infrastructure.service.CourseService;
import tvz.btot.zavrsni.infrastructure.service.CrudController;
import tvz.btot.zavrsni.security.preauthorization.AllowAdmin;
import tvz.btot.zavrsni.security.preauthorization.AllowAnonymous;
import tvz.btot.zavrsni.security.preauthorization.AllowSuperadmin;
import tvz.btot.zavrsni.security.preauthorization.AllowTeacher;
import tvz.btot.zavrsni.web.dto.CourseDto;
import tvz.btot.zavrsni.web.dto.SubjectDto;
import tvz.btot.zavrsni.web.form.CourseForm;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController implements CrudController<CourseDto, CourseForm, Integer> {
    private final CourseService courseService;

    public CourseController(final CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    @GetMapping
    @AllowAnonymous
    public ResponseEntity<List<CourseDto>> findAll() {
        System.out.println("CouseController::findAll");
        return ResponseEntity
                .ok(courseService.getAll());
    }

    @Override
    @GetMapping("/{courseId}")
    @AllowAnonymous
    public ResponseEntity<CourseDto> findById(final @PathVariable Integer courseId) {
        return ResponseEntity
                .ok(courseService.findById(courseId));
    }

    @Override
    @AllowAdmin
    @PostMapping
    public ResponseEntity<CourseDto> create(final @RequestBody CourseForm courseForm) {
        return ResponseEntity
                .ok(courseService.create(courseForm));
    }

    @Override
    @AllowSuperadmin
    @GetMapping("/{courseId}/form")
    public ResponseEntity<CourseForm> getFormById(final @PathVariable Integer courseId) {
        return ResponseEntity
                .ok(courseService.getForm(courseId));
    }

    @Override
    @AllowSuperadmin
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> delete(final @PathVariable Integer courseId) {
        courseService.delete(courseId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @Override
    @AllowSuperadmin
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDto> update(final @PathVariable Integer courseId,
                                            final @RequestBody CourseForm courseForm) {
        return ResponseEntity
                .ok(courseService.update(courseId, courseForm));
    }

    @AllowAdmin
    @AllowTeacher
    @PostMapping("/{courseId}/subject")
    public ResponseEntity<SubjectDto> addSubject(final @PathVariable Integer courseId,
                                                 final @RequestBody Integer subjectId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseService.addSubject(courseId, subjectId));
    }

    @AllowSuperadmin
    @DeleteMapping("/{courseId}/subject/{subjectId}")
    public ResponseEntity<Void> removeSubject(final @PathVariable Integer courseId,
                                               final @PathVariable Integer subjectId) {
        courseService.removeSubject(courseId, subjectId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
