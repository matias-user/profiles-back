package mis.projects.users.profiles.services;

import java.util.List;
import java.util.Map;

import mis.projects.users.profiles.models.User;

public interface UserService {
    List<User> findAll();
    User findById(int id);
    User updateById(int id, User user);
    Map<String, Object> deleteById(int id);
}
