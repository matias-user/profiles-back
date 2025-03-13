package mis.projects.users.profiles.services;

import java.util.List;
import java.util.Map;

import mis.projects.users.profiles.models.Role;


public interface RoleService {
    List<Role> findAll();
    Role findById(int id);
    Role updateById(int id, Role role);
    Map<String, Object> deleteById(int id);
    Role create(Role role);
}
