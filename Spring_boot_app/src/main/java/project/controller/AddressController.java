//Generated from controller.template file

package rs.ac.ftn.uns.jsd.foo.controller;

import org.springframework.http.ResponseEntity;
//Generated from service.template file

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;


@RestController
@RequestMapping("/api/addresss")
public class AddressController {
    private static final Logger LOGGER = LogManager.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.ok(addressService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getById(id));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Address address) {
        return ResponseEntity.ok(addressService.create(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @Valid @RequestBody Address address) {
        if (addressService.getById(id) == null) {
            LOGGER.error("Id " + id + " does not exists");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(addressService.update(id, address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Address address = addressService.getById(id);
        if (address == null) {
            LOGGER.error("Id " + id + " does not exists");
            ResponseEntity.badRequest().build();
        }

        addressService.delete(id);

        return ResponseEntity.ok().build();
    }
}