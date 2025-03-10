package mis.projects.users.profiles.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mis.projects.users.profiles.models.Role;
import mis.projects.users.profiles.repositories.RoleRepository;

public class RoleServiceImp implements RoleService {

    private RoleRepository repo;

    @Override
    public List<Role> findAll() {
        return repo.findAll();
    }

    @Override
    public Role findById(int id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Role updateById(int id, Role role) {
        Role roleToUpdate = repo.findById(id).get();
        roleToUpdate.setName(role.getName());
        return repo.save(roleToUpdate);
    }

    @Override
    public Map<String, Object> deleteById(int id) {
        Role role = repo.findById(id).get();
        Map<String, Object> json = new HashMap<>();
        repo.delete(role);
        json.put("message", "Rol con id "+id+" ha sido satisfactoriamente eliminado.");
        return json;
    }
    
}
