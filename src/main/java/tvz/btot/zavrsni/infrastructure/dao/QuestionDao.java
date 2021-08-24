package tvz.btot.zavrsni.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tvz.btot.zavrsni.domain.Question;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Mapper
public interface QuestionDao {
    List<Question> findAll(@Param("params") SqlQueryParams params);
    List<Question> findAllByExamId(@Param("params") SqlQueryParams params);
    Question findById(@Param("params") SqlQueryParams params);
    void create(@Param("params") SqlQueryParams params);
    void update(@Param("params") SqlQueryParams params);
    void delete(@Param("params") SqlQueryParams params);
    Integer createAnswer(@Param("params") SqlQueryParams params);
    void updateAnswer(@Param("params") SqlQueryParams params);
}
