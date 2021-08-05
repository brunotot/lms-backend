package tvz.btot.zavrsni.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.service.UserService;
import tvz.btot.zavrsni.infrastructure.utils.JsonObject;
import tvz.btot.zavrsni.security.JwtTokenProvider;
import tvz.btot.zavrsni.web.dto.JwtPayloadDto;
import tvz.btot.zavrsni.web.dto.UserDto;
import tvz.btot.zavrsni.web.form.LoginForm;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(final UserService userService,
                          final JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtPayloadDto authenticate(@RequestBody final LoginForm loginForm) {
        final String username = loginForm.getUsername();
        final String password = loginForm.getPassword();
        return this.userService.authenticate(username, password);
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/")
    public String ping() {
        return JsonObject.builder()
                .add("username", "TestUsername")
                .add("username", "TestPassword")
                .stringify();
    }

}
