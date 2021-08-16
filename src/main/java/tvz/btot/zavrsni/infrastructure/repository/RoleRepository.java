package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> getAll();
}
