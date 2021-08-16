package tvz.btot.zavrsni.infrastructure.service;

import tvz.btot.zavrsni.web.dto.CourseDto;
import tvz.btot.zavrsni.web.dto.SubjectDto;
import tvz.btot.zavrsni.web.form.CourseForm;

import java.util.List;

public interface CourseService {
    List<CourseDto> getAll();
    CourseDto create(CourseForm courseForm);
    CourseForm getForm(Integer courseId);
    void delete(Integer courseId);
    CourseDto update(Integer courseId, CourseForm courseForm);
    CourseDto findById(Integer courseId);
    SubjectDto addSubject(Integer courseId, Integer subjectId);
    void removeSubject(Integer courseId, Integer subjectId);
}
