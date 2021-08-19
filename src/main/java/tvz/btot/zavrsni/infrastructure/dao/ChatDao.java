package tvz.btot.zavrsni.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tvz.btot.zavrsni.domain.Chat;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Mapper
public interface ChatDao {
    List<Chat> findExistingChats(@Param("params") SqlQueryParams params);
    Chat findById(@Param("params") SqlQueryParams params);
    void create(@Param("params") SqlQueryParams params);
    Chat findIndividualChat(@Param("params") SqlQueryParams params);
}
