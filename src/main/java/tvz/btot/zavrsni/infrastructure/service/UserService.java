package tvz.btot.zavrsni.infrastructure.service;

import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.web.dto.JwtPayloadDto;
import tvz.btot.zavrsni.web.dto.UserDto;
import tvz.btot.zavrsni.web.form.UserForm;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    List<UserDto> findAll();
    JwtPayloadDto authenticate(String username, String password);
    String create(UserForm form);
    boolean deleteByUsername(String username);
}
