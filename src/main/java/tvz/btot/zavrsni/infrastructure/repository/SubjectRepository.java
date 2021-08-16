package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.Subject;
import tvz.btot.zavrsni.web.form.SubjectForm;

import java.util.List;

public interface SubjectRepository {
    List<Subject> getAllByUserId(int userId);
    List<Subject> getAllByCourseId(int courseId);
    Subject findById(Integer subjectId);
    void delete(Integer subjectId);
    List<Subject> findAll();
    Subject update(SubjectForm form);
    Subject create(SubjectForm form);
    void setCourses(Integer subjectId, List<Integer> courseIds);
}
