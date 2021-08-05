package tvz.btot.zavrsni.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tvz.btot.zavrsni.infrastructure.service.CourseService;
import tvz.btot.zavrsni.infrastructure.service.SubjectService;
import tvz.btot.zavrsni.web.dto.CourseDto;
import tvz.btot.zavrsni.web.dto.SubjectDto;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(final SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(params = "courseId")
    public List<SubjectDto> getAllByCourseId(@RequestParam("courseId") Integer courseId) {
        return subjectService.getAllByCourseId(courseId);
    }

    @GetMapping(params = "userId")
    public List<SubjectDto> getAllByUserId(@RequestParam("userId") Integer userId) {
        return subjectService.getAllByUserId(userId);
    }

    @GetMapping("/{subjectId}")
    public SubjectDto getSubjectById(@PathVariable Integer subjectId) {
        return subjectService.findById(subjectId);
    }
}
