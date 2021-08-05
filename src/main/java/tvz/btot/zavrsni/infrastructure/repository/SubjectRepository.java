package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.Subject;

import java.util.List;

public interface SubjectRepository {
    List<Subject> getAllByUserId(int userId);
    List<Subject> getAllByCourseId(int courseId);
    Subject findById(Integer subjectId);
}
