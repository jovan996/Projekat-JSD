//Generated from controller.template file

package rs.ac.ftn.uns.jsd.foo.controller;

import org.springframework.http.ResponseEntity;
//Generated from service.template file

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;


@RestController
@RequestMapping("/api/firms")
public class FirmController {
    private static final Logger LOGGER = LogManager.getLogger(FirmController.class);

    @Autowired
    private FirmService firmService;

    @GetMapping
    public ResponseEntity<List<Firm>> getAll() {
        return ResponseEntity.ok(firmService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Firm> getById(@PathVariable Long id) {
        return ResponseEntity.ok(firmService.getById(id));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Firm firm) {
        return ResponseEntity.ok(firmService.create(firm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Firm> update(@PathVariable Long id, @Valid @RequestBody Firm firm) {
        if (firmService.getById(id) == null) {
            LOGGER.error("Id " + id + " does not exists");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(firmService.update(id, firm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Firm firm = firmService.getById(id);
        if (firm == null) {
            LOGGER.error("Id " + id + " does not exists");
            ResponseEntity.badRequest().build();
        }

        firmService.delete(id);

        return ResponseEntity.ok().build();
    }
}