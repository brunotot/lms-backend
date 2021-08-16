package tvz.btot.zavrsni.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tvz.btot.zavrsni.domain.Course;
import tvz.btot.zavrsni.domain.Subject;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Mapper
public interface CourseDao {
    Course findById(@Param("params") SqlQueryParams params);
    List<Course> getAll(@Param("params") SqlQueryParams params);

    Integer create(@Param("params") SqlQueryParams params);
    Subject addSubject(@Param("params") SqlQueryParams params);

    void update(@Param("params") SqlQueryParams params);

    void delete(@Param("params") SqlQueryParams params);
    void removeSubject(@Param("params") SqlQueryParams params);
}
