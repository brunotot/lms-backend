package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.AnnouncementType;

import java.util.List;

public interface AnnouncementTypeRepository {
    List<AnnouncementType> getAll();
}
