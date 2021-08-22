package tvz.btot.zavrsni.infrastructure.service;

import tvz.btot.zavrsni.domain.AnnouncementType;
import tvz.btot.zavrsni.domain.Role;

import java.util.List;

public interface AnnouncementTypeService {
    List<AnnouncementType> getAll();
}
