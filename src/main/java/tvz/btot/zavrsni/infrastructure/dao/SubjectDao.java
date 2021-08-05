package tvz.btot.zavrsni.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tvz.btot.zavrsni.domain.Subject;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Mapper
public interface SubjectDao {
    List<Subject> getAllByCourseId(@Param("params") SqlQueryParams params);
    List<Subject> getAllByUserId(@Param("params") SqlQueryParams params);
    Subject findById(@Param("params") SqlQueryParams id);
}
