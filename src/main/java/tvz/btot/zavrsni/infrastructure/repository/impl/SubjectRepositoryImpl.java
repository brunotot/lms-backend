package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import tvz.btot.zavrsni.domain.Course;
import tvz.btot.zavrsni.domain.Subject;
import tvz.btot.zavrsni.infrastructure.dao.CourseDao;
import tvz.btot.zavrsni.infrastructure.dao.SubjectDao;
import tvz.btot.zavrsni.infrastructure.repository.CourseRepository;
import tvz.btot.zavrsni.infrastructure.repository.SubjectRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Repository
public class SubjectRepositoryImpl implements SubjectRepository {
    private final SubjectDao subjectDao;

    public SubjectRepositoryImpl(final SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public List<Subject> getAllByUserId(int userId) {
        return subjectDao.getAllByUserId(SqlQueryParams.newInstance("userId", userId));
    }

    @Override
    public List<Subject> getAllByCourseId(int courseId) {
        return subjectDao.getAllByCourseId(SqlQueryParams.newInstance("courseId", courseId));
    }

    @Override
    public Subject findById(final Integer subjectId) {
        return subjectDao.findById(SqlQueryParams.newInstance("id", subjectId));
    }
}
