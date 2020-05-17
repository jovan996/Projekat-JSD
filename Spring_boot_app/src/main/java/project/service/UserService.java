//Generated from service.template file


import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.jsd.foo.model.User;
import rs.ac.ftn.uns.jsd.foo.repository.UserRepository;
import rs.ac.ftn.uns.jsd.foo.repository.UserService;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        User oldUser = userRepository.findById(id);
        oldUser.setId(user.getId);
        oldUser.setFirstName(user.getFirstName);
        oldUser.setLastName(user.getLastName);
        oldUser.setUsername(user.getUsername);
        oldUser.setPassword(user.getPassword);
        oldUser.setActive(user.getActive);
        User updatedUser = userRepository.save(oldUser);
        return updatedUser;
    }

    public void delete(Long id) {
        return userRepository.deleteById(id);
    }