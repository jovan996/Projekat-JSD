//Generated from service.template file


import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.jsd.foo.model.Firm;
import rs.ac.ftn.uns.jsd.foo.repository.FirmRepository;
import rs.ac.ftn.uns.jsd.foo.repository.FirmService;

@Service
public class FirmService {

    @Autowired
    private FirmRepository firmRepository;

    public List<Firm> getAll() {
        return firmRepository.findAll();
    }

    public Firm getById(Long id) {
        return firmRepository.findById(id);
    }

    public Firm create(Firm firm) {
        return firmRepository.save(firm);
    }

    public Firm update(Long id, Firm firm) {
        Firm oldFirm = firmRepository.findById(id);
        oldFirm.setId(firm.getId);
        oldFirm.setName(firm.getName);
        oldFirm.setEmail(firm.getEmail);
        oldFirm.setWorkersNumber(firm.getWorkersNumber);
        oldFirm.setDescription(firm.getDescription);
        oldFirm.setAddress(firm.getAddress);
        Firm updatedFirm = firmRepository.save(oldFirm);
        return updatedFirm;
    }

    public void delete(Long id) {
        return firmRepository.deleteById(id);
    }