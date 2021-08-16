package tvz.btot.zavrsni.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tvz.btot.zavrsni.domain.Message;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Mapper
public interface MessageDao {
    List<Message> findAllFromChat(@Param("params") SqlQueryParams chatId);

    Integer create(@Param("params") SqlQueryParams params);
}
