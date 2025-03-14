package mis.projects.users.profiles.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mis.projects.users.profiles.exception.RoleNotFoundException;
import mis.projects.users.profiles.exception.UserNotFoundException;
import mis.projects.users.profiles.models.Role;
import mis.projects.users.profiles.models.User;
import mis.projects.users.profiles.repositories.RoleRepository;
import mis.projects.users.profiles.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService {

    private UserRepository repo;
    private RoleRepository roleRepo;

    public UserServiceImp(UserRepository repo, RoleRepository roleRepo) {
        this.repo = repo;
        this.roleRepo = roleRepo;
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User findById(int id) {
        return repo.findById(id).orElseThrow( () -> new UserNotFoundException("Usuario con id: "+id +" no ha sido encontrado") );
    }

    @Override
    public User updateById(int id, User user) {
        User userToUpdate = repo.findById(id).orElseThrow( () -> new UserNotFoundException("Usuario con id: "+id +" no ha sido encontrado") );
        userToUpdate.setId(0);
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setLastname(user.getLastname());
        userToUpdate.setPhoneNumber(user.getPhoneNumber());
        userToUpdate.setRoles(user.getRoles());

        return repo.save(userToUpdate);
    }

    @Override
    public Map<String, Object> deleteById(int id) {
        User user = repo.findById(id).orElseThrow( () -> new UserNotFoundException("Usuario con id: "+id +" no ha sido encontrado") );
        repo.delete(user);
        Map<String, Object> json = new HashMap<>();
        json.put("message", "Usuario con id: " + id + "ha sido correctamente eliminado");
        return json;
    }

    @Override
    @Transactional
    public User assignRoles(int user_id, List<String> roles) {
        User user = repo.findById(user_id).orElseThrow( () -> new UserNotFoundException("Usuario con id: "+user_id +" no ha sido encontrado") );
        List<Role> rolesList = roleRepo.findByNameIn(roles);

        // Validar si existen los roles
        if (rolesList.size() != roles.size()) {
            List<String> foundRoles = rolesList.stream()
                    .map(Role::getName)
                    .collect(Collectors.toList());
            List<String> missingRoles = roles.stream()
                    .filter(role -> !foundRoles.contains(role))
                    .collect(Collectors.toList());
            throw new RoleNotFoundException("Roles no encontrados: " + missingRoles.toString());
        }

        user.setRoles(rolesList);

        return repo.save(user);
    }

    @Override
    public User create(User user) {
        return repo.save(user);
    }

}
