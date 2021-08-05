package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.Course;
import tvz.btot.zavrsni.domain.User;

import java.util.List;

public interface CourseRepository {
    List<Course> getAll();
}
