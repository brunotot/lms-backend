package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tvz.btot.zavrsni.domain.Course;
import tvz.btot.zavrsni.domain.Subject;
import tvz.btot.zavrsni.infrastructure.dao.CourseDao;
import tvz.btot.zavrsni.infrastructure.dao.SubjectDao;
import tvz.btot.zavrsni.infrastructure.repository.CourseRepository;
import tvz.btot.zavrsni.infrastructure.repository.SubjectRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;
import tvz.btot.zavrsni.web.converter.SubjectConverter;
import tvz.btot.zavrsni.web.form.SubjectForm;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Repository
public class SubjectRepositoryImpl implements SubjectRepository {
    private final SubjectDao subjectDao;
    private final SubjectConverter subjectConverter;

    public SubjectRepositoryImpl(final SubjectDao subjectDao,
                                 final SubjectConverter subjectConverter) {
        this.subjectDao = subjectDao;
        this.subjectConverter = subjectConverter;
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
        return Optional.ofNullable(subjectDao.findById(SqlQueryParams.newInstance("id", subjectId)))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void delete(final Integer subjectId) {
        subjectDao.delete(SqlQueryParams.newInstance("subjectId", subjectId));
    }

    @Override
    public List<Subject> findAll() {
        return subjectDao.findAll(SqlQueryParams.newInstance());
    }

    @Override
    public Subject update(final SubjectForm form) {
        Subject subject = subjectConverter.formToSource(form);
        String courseIdsQuery = "(" + subject.getCourseIds().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        subjectDao.update(SqlQueryParams.newInstance(subject).param("courseIdsQuery", courseIdsQuery));
        return subject;
    }

    @Override
    @Transactional
    public Subject create(final SubjectForm form) {
        Subject subject = subjectConverter.formToSource(form);
        SqlQueryParams params = SqlQueryParams.newInstance(subject);
        subjectDao.create(params);
        Integer newId = params.getOutputParam("newSubjectId", Integer.class);
        subject.setId(newId);
        setCourses(newId, subject.getCourseIds());
        return subject;
    }

    @Override
    public void setCourses(final Integer subjectId,
                           final List<Integer> courseIds) {
        subjectDao.setCourses(SqlQueryParams.newInstance()
            .param("courseIds", courseIds)
            .param("subjectId", subjectId));
    }
}
