package tvz.btot.zavrsni.web.controller;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tvz.btot.zavrsni.domain.Role;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.service.UserService;
import tvz.btot.zavrsni.security.preauthorization.AllowAdmin;
import tvz.btot.zavrsni.security.preauthorization.AllowAnonymous;
import tvz.btot.zavrsni.security.preauthorization.AllowStudent;
import tvz.btot.zavrsni.security.preauthorization.AllowSuperadmin;
import tvz.btot.zavrsni.security.utils.SecurityContextUtils;
import tvz.btot.zavrsni.web.controller.base.CrudController;
import tvz.btot.zavrsni.web.dto.JwtPayloadDto;
import tvz.btot.zavrsni.web.dto.UserDto;
import tvz.btot.zavrsni.web.form.LoginForm;
import tvz.btot.zavrsni.web.form.UserForm;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController implements CrudController<UserDto, UserForm, Integer> {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @AllowAnonymous
    @PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtPayloadDto> authenticate(@RequestBody final LoginForm loginForm) {
        return ResponseEntity
                .ok(this.userService.authenticate(loginForm.getUsername(), loginForm.getPassword()));
    }

    @Override
    @AllowSuperadmin
    @PostMapping
    public ResponseEntity<UserDto> create(final @RequestBody UserForm userForm) {
        final UserDto createdUserDto = this.userService.createUser(userForm);
        final String uri = String.format("/user/%s", createdUserDto.getId());
        return ResponseEntity
                .created(URI.create(uri))
                .body(createdUserDto);
    }

    @Override
    @GetMapping
    @AllowAnonymous
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity
                .ok(this.userService.findAll());
    }

    @Override
    @GetMapping("/{userId}")
    @AllowAnonymous
    @SneakyThrows
    public ResponseEntity<UserDto> findById(final @PathVariable Integer userId) {
        return ResponseEntity
                .ok(this.userService.findById(userId));
    }

    @Override
    @PutMapping("/{userId}")
    @AllowStudent
    public ResponseEntity<UserDto> update(final @PathVariable Integer userId,
                                          final @RequestBody UserForm userForm) {
        if (this.isUserNotAllowed(userId)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }
        return ResponseEntity
                .ok(this.userService.update(userId, userForm));
    }

    @Override
    @GetMapping("/{userId}/form")
    @AllowStudent
    public ResponseEntity<UserForm> getFormById(final @PathVariable Integer userId) {
        if (this.isUserNotAllowed(userId)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }
        return ResponseEntity
                .ok(this.userService.getForm(userId));
    }

    @Override
    @DeleteMapping("/{userId}")
    @AllowAdmin
    public ResponseEntity<Void> delete(final @PathVariable Integer userId) {
       userService.delete(userId);
       return ResponseEntity
               .noContent()
               .build();
    }

    private boolean isUserNotAllowed(final Integer chosenUserId) {
        final User thisUser = SecurityContextUtils.getUser();
        return !thisUser.getRoles().contains(Role.SUPERADMIN) && !thisUser.getId().equals(chosenUserId);
    }
}
