package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import tvz.btot.zavrsni.domain.Course;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.dao.CourseDao;
import tvz.btot.zavrsni.infrastructure.dao.UserDao;
import tvz.btot.zavrsni.infrastructure.repository.CourseRepository;
import tvz.btot.zavrsni.infrastructure.repository.UserRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
    private final CourseDao courseDao;

    public CourseRepositoryImpl(final CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> getAll() {
        return courseDao.getAll(SqlQueryParams.newInstance());
    }
}
