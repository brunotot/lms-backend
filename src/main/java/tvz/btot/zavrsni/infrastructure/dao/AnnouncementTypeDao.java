package tvz.btot.zavrsni.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tvz.btot.zavrsni.domain.AnnouncementType;
import tvz.btot.zavrsni.domain.Role;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Mapper
public interface AnnouncementTypeDao {
    List<AnnouncementType> getAll(@Param("params") SqlQueryParams params);
}
