package mis.projects.users.profiles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mis.projects.users.profiles.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
