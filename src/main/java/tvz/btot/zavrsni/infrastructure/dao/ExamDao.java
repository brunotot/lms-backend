package tvz.btot.zavrsni.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tvz.btot.zavrsni.domain.Exam;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Mapper
public interface ExamDao {
    List<Exam> findAllBySubjectId(@Param("params") SqlQueryParams subjectId);
    Exam findById(@Param("params") SqlQueryParams examId);

    Integer create(@Param("params") SqlQueryParams newInstance);

    void update(@Param("params") SqlQueryParams newInstance);

    void delete(@Param("params") SqlQueryParams examId);
}
