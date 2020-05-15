//Generated from service.template file


import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.jsd.foo.model.Address;
import rs.ac.ftn.uns.jsd.foo.repository.AddressRepository;
import rs.ac.ftn.uns.jsd.foo.repository.AddressService;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address getById(Long id) {
        return addressRepository.findById(id);
    }

    public Address create(Address address) {
        return addressRepository.save(address);
    }

    public Address update(Long id, Address address) {
        Address oldAddress = addressRepository.findById(id);
        oldAddress.setId(address.getId);
        oldAddress.setStreet(address.getStreet);
        oldAddress.setNumber(address.getNumber);
        oldAddress.setCity(address.getCity);
        oldAddress.setCountry(address.getCountry);
        Address updatedAddress = addressRepository.save(oldAddress);
        return updatedAddress;
    }

    public void delete(Long id) {
        return addressRepository.deleteById(id);
    }