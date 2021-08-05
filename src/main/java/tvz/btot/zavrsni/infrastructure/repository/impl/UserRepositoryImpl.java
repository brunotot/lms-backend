package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.dao.UserDao;
import tvz.btot.zavrsni.infrastructure.repository.UserRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    public UserRepositoryImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(final User user) {
        final SqlQueryParams params = SqlQueryParams.newInstance("user", user);
        Integer id = this.userDao.create(params);
        user.setId(id);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll(SqlQueryParams.newInstance());
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(SqlQueryParams.newInstance("id", id));
    }

    @Override
    public User findByUsername(final String username) {
        final SqlQueryParams params = SqlQueryParams.newInstance("username", username);
        return this.userDao.findByUsername(params);
    }

    @Override
    public Boolean existsByUsername(final String username) {
        final SqlQueryParams params = SqlQueryParams.newInstance("username", username);
        return this.userDao.existsByUsername(params);
    }

    @Override
    public Boolean deleteByUsername(final String username) {
        final SqlQueryParams params = SqlQueryParams.newInstance("username", username);
        return this.userDao.deleteByUsername(params);
    }

}
