package tvz.btot.zavrsni.infrastructure.service.impl;

import org.springframework.stereotype.Service;
import tvz.btot.zavrsni.domain.Role;
import tvz.btot.zavrsni.infrastructure.repository.RoleRepository;
import tvz.btot.zavrsni.infrastructure.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return this.roleRepository.getAll();
    }
}