package tvz.btot.zavrsni.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tvz.btot.zavrsni.domain.Subject;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Mapper
public interface SubjectDao {
    Subject findById(@Param("params") SqlQueryParams params);
    List<Subject> findAll(@Param("params") SqlQueryParams params);
    List<Subject> getAllByCourseId(@Param("params") SqlQueryParams params);
    List<Subject> getAllByUserId(@Param("params") SqlQueryParams params);

    Integer create(@Param("params") SqlQueryParams params);
    void setCourses(@Param("params") SqlQueryParams params);

    void update(@Param("params") SqlQueryParams params);

    void delete(@Param("params") SqlQueryParams params);
}
