package tvz.btot.zavrsni.infrastructure.service.impl;

import org.springframework.stereotype.Service;
import tvz.btot.zavrsni.domain.AnnouncementType;
import tvz.btot.zavrsni.domain.Role;
import tvz.btot.zavrsni.infrastructure.repository.AnnouncementTypeRepository;
import tvz.btot.zavrsni.infrastructure.repository.RoleRepository;
import tvz.btot.zavrsni.infrastructure.service.AnnouncementTypeService;
import tvz.btot.zavrsni.infrastructure.service.RoleService;

import java.util.List;

@Service
public class AnnouncementTypeServiceImpl implements AnnouncementTypeService {
    private final AnnouncementTypeRepository announcementTypeRepository;

    public AnnouncementTypeServiceImpl(final AnnouncementTypeRepository announcementTypeRepository) {
        this.announcementTypeRepository = announcementTypeRepository;
    }

    @Override
    public List<AnnouncementType> getAll() {
        return this.announcementTypeRepository.getAll();
    }
}