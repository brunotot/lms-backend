package tvz.btot.zavrsni.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tvz.btot.zavrsni.domain.Announcement;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Mapper
public interface AnnouncementDao {
    List<Announcement> findAll(@Param("params") SqlQueryParams params);
    List<Announcement> findActiveAnnouncementsBySubjectId(@Param("params") SqlQueryParams params);
    Announcement findById(@Param("params") SqlQueryParams params);
    void create(@Param("params") SqlQueryParams params);
    void update(@Param("params") SqlQueryParams params);
    void delete(@Param("params") SqlQueryParams params);
}
