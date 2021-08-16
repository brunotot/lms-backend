package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.web.form.UserForm;

import java.util.List;

public interface UserRepository {
    User create(UserForm form);
    List<User> findAll();
    User findById(Integer id);
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean deleteByUsername(String username);
    void delete(Integer userId);
    User getForm(Integer userId);
    User update(Integer userId, UserForm userForm);
}
