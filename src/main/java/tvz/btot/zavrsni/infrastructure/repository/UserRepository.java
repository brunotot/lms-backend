package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.User;

import java.util.List;

public interface UserRepository {
    User create(User user);
    List<User> findAll();
    User findById(Integer id);
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean deleteByUsername(String username);
}
