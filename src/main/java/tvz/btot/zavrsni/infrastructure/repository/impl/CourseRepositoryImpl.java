package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import tvz.btot.zavrsni.domain.Course;
import tvz.btot.zavrsni.domain.Subject;
import tvz.btot.zavrsni.infrastructure.dao.CourseDao;
import tvz.btot.zavrsni.infrastructure.repository.CourseRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;
import tvz.btot.zavrsni.web.converter.CourseConverter;
import tvz.btot.zavrsni.web.form.CourseForm;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
    private final CourseDao courseDao;
    private final CourseConverter courseConverter;

    public CourseRepositoryImpl(final CourseDao courseDao,
                                final CourseConverter courseConverter) {
        this.courseDao = courseDao;
        this.courseConverter = courseConverter;
    }

    @Override
    public List<Course> getAll() {
        return courseDao.getAll(SqlQueryParams.newInstance());
    }

    @Override
    public Course create(final CourseForm courseForm) {
        Course course = courseConverter.formToSource(courseForm);
        SqlQueryParams params = SqlQueryParams.newInstance(course);
        courseDao.create(params);
        Integer newId = params.getOutputParam("newCourseId", Integer.class);
        course.setId(newId);
        return course;
    }

    @Override
    public CourseForm getForm(final Integer courseId) {
        Course course = Optional.ofNullable(courseDao.findById(SqlQueryParams.newInstance("courseId", courseId)))
                .orElseThrow(ResourceNotFoundException::new);
        return courseConverter.sourceToForm(course);
    }

    @Override
    public void delete(final Integer courseId) {
        courseDao.delete(SqlQueryParams.newInstance("courseId", courseId));
    }

    @Override
    public Course update(final Integer courseId, final CourseForm courseForm) {
        courseForm.setId(courseId);
        Course course = courseConverter.formToSource(courseForm);
        courseDao.update(SqlQueryParams.newInstance(course));
        return course;
    }

    @Override
    public Course findById(final Integer courseId) {
       return Optional.ofNullable(courseDao.findById(SqlQueryParams.newInstance("courseId", courseId)))
               .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Subject addSubject(Integer courseId, Integer subjectId) {
        return courseDao.addSubject(SqlQueryParams.newInstance()
            .param("courseId", courseId)
            .param("subjectId", subjectId));
    }

    @Override
    public void removeSubject(Integer courseId, Integer subjectId) {
        courseDao.removeSubject(SqlQueryParams.newInstance()
                .param("courseId", courseId)
                .param("subjectId", subjectId));
    }
}
