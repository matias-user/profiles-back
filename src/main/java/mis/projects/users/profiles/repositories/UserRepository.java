package mis.projects.users.profiles.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mis.projects.users.profiles.models.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
