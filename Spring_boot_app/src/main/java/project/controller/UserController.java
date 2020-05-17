//Generated from controller.template file

package rs.ac.ftn.uns.jsd.foo.controller;

import org.springframework.http.ResponseEntity;
//Generated from service.template file

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user) {
        if (userService.getById(id) == null) {
            LOGGER.error("Id " + id + " does not exists");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            LOGGER.error("Id " + id + " does not exists");
            ResponseEntity.badRequest().build();
        }

        userService.delete(id);

        return ResponseEntity.ok().build();
    }
}