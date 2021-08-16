package tvz.btot.zavrsni.infrastructure.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.errorhandling.ApiException;
import tvz.btot.zavrsni.infrastructure.repository.UserRepository;
import tvz.btot.zavrsni.infrastructure.service.UserService;
import tvz.btot.zavrsni.security.jwt.JwtTokenProvider;
import tvz.btot.zavrsni.web.converter.UserConverter;
import tvz.btot.zavrsni.web.dto.JwtPayloadDto;
import tvz.btot.zavrsni.web.dto.UserDto;
import tvz.btot.zavrsni.web.form.UserForm;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final String USER_NOT_FOUND_TITLE = "No user with given username";
    private static final String USER_NOT_FOUND_DESCRIPTION = "The user doesn't exist";
    private static final String USERNAME_EXISTS_TITLE = "Username exists";
    private static final String USERNAME_EXISTS_DESCRIPTION = "Username is already in use";
    private static final String BAD_CREDENTIALS_TITLE = "Bad credentials";
    private static final String BAD_CREDENTIALS_DESCRIPTION = "Invalid username/password supplied";

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(final UserRepository userRepository,
                           final JwtTokenProvider jwtTokenProvider,
                           final AuthenticationManager authenticationManager,
                           final UserConverter userConverter) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userConverter = userConverter;
    }

    @Override
    public User findByUsername(String username) {
        return Optional
                .ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, USER_NOT_FOUND_TITLE, USER_NOT_FOUND_DESCRIPTION));
    }

    @Override
    public List<UserDto> findAll() {
        return userConverter.sourceToDtoList(userRepository.findAll());
    }

    @Override
    public JwtPayloadDto authenticate(final String username, final String password) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(authToken);
            User user = findByUsername(username);
            String token = jwtTokenProvider.createToken(user);
            UserDto userDto = userConverter.sourceToDto(user);
            return JwtPayloadDto.builder()
                    .user(userDto)
                    .token(token)
                    .build();
        } catch (AuthenticationException e) {
            throw new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, BAD_CREDENTIALS_TITLE, BAD_CREDENTIALS_DESCRIPTION);
        }
    }

    @Override
    public UserDto createUser(UserForm userForm) {
        return userConverter.sourceToDto(userRepository.create(userForm));
    }

    @Override
    public UserDto findById(Integer userId) {
        return userConverter.sourceToDto(userRepository.findById(userId));
    }

    @Override
    public void delete(Integer userId) {
        userRepository.delete(userId);
    }

    @Override
    public UserForm getForm(Integer userId) {
        return userConverter.sourceToForm(userRepository.getForm(userId));
    }

    @Override
    public UserDto update(Integer userId, UserForm userForm) {
        return userConverter.sourceToDto(userRepository.update(userId, userForm));
    }
}
