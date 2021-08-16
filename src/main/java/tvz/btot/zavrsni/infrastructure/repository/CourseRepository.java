package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.Course;
import tvz.btot.zavrsni.domain.Subject;
import tvz.btot.zavrsni.web.form.CourseForm;

import java.util.List;

public interface CourseRepository {
    List<Course> getAll();
    Course create(CourseForm courseForm);
    CourseForm getForm(Integer courseId);
    void delete(Integer courseId);
    Course update(Integer courseId, CourseForm courseForm);
    Course findById(Integer courseId);
    Subject addSubject(Integer courseId, Integer subjectId);
    void removeSubject(Integer courseId, Integer subjectId);
}
