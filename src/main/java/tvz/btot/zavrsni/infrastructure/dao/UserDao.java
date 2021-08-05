package tvz.btot.zavrsni.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> findAllInGroupChat(@Param("params") SqlQueryParams params);
    User findById(@Param("params") SqlQueryParams params);
    User findByUsername(@Param("params") SqlQueryParams params);
    List<User> findAll(@Param("params") SqlQueryParams params);
    Integer create(@Param("params") SqlQueryParams params);
    Boolean existsByUsername(@Param("params") SqlQueryParams params);
    Boolean deleteByUsername(@Param("params") SqlQueryParams params);
}
