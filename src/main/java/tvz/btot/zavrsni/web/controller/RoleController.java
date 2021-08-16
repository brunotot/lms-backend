package tvz.btot.zavrsni.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tvz.btot.zavrsni.domain.Role;
import tvz.btot.zavrsni.infrastructure.service.RoleService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAll() {
        return ResponseEntity
            .ok(roleService.getAll());
    }
}
