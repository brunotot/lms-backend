package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import tvz.btot.zavrsni.domain.Role;
import tvz.btot.zavrsni.infrastructure.dao.RoleDao;
import tvz.btot.zavrsni.infrastructure.repository.CourseRepository;
import tvz.btot.zavrsni.infrastructure.repository.RoleRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;
import tvz.btot.zavrsni.web.converter.CourseConverter;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private final RoleDao roleDao;

    public RoleRepositoryImpl(final RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAll() {
        return roleDao.getAll(SqlQueryParams.newInstance());
    }
}
