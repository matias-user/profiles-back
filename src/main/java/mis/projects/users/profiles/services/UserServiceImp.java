package mis.projects.users.profiles.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import mis.projects.users.profiles.models.User;
import mis.projects.users.profiles.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService {

    private UserRepository repo;
    
    public UserServiceImp(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User findById(int id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public User updateById(int id, User user) {
        User userToUpdate = repo.findById(id).orElseThrow();
        userToUpdate.setId(0);
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setName(user.getName());
        userToUpdate.setLastname(user.getLastname());
        userToUpdate.setPhoneNumber(user.getPhoneNumber());
        userToUpdate.setRoles(user.getRoles());

        return repo.save(userToUpdate);
    }

    @Override
    public Map<String, Object> deleteById(int id) {
        User user = repo.findById(id).get();
        repo.delete(user);
        Map<String, Object> json = new HashMap<>();
        json.put("message", "Usuario con id: "+ id+ "ha sido correctamente eliminado");
        return json;
    }
    
    
}
