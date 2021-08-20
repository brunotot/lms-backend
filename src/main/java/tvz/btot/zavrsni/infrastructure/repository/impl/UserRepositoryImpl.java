package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.dao.UserDao;
import tvz.btot.zavrsni.infrastructure.repository.UserRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;
import tvz.btot.zavrsni.web.converter.UserConverter;
import tvz.btot.zavrsni.web.form.UserForm;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;
    private final UserConverter userConverter;

    public UserRepositoryImpl(final UserDao userDao,
                              final UserConverter userConverter) {
        this.userDao = userDao;
        this.userConverter = userConverter;
    }

    @Override
    public User create(final UserForm userForm) {
        final User user = userConverter.formToSource(userForm);
        final SqlQueryParams params = SqlQueryParams.newInstance("user", user);
        this.userDao.create(params);
        Integer id = params.getOutputParam("newUserId", Integer.class);
        user.setId(id);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll(SqlQueryParams.newInstance());
    }

    @Override
    public User findById(final Integer id) {
        return Optional.ofNullable(userDao.findById(SqlQueryParams.newInstance("id", id)))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public User findByUsername(final String username) {
        return Optional.ofNullable(this.userDao.findByUsername(SqlQueryParams.newInstance("username", username)))
                .orElseThrow(ResourceNotFoundException::new);
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

    @Override
    public void delete(final Integer userId) {
        userDao.delete(SqlQueryParams.newInstance("userId", userId));
    }

    @Override
    public User getForm(final Integer userId) {
        return userDao.getForm(SqlQueryParams.newInstance("userId", userId));
    }

    @Override
    public User update(final Integer userId, final UserForm userForm) {
        User updatedUser = userConverter.formToSource(userForm);
        updatedUser.setId(userId);
        userDao.update(SqlQueryParams.newInstance("user", updatedUser));
        return updatedUser;
    }

}
