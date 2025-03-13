package mis.projects.users.profiles.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mis.projects.users.profiles.models.User;
import mis.projects.users.profiles.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    
    private UserService service;

    public UserController(@Qualifier("userServiceImp") UserService userService) {
        service = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{user_id}")
    public User getUserById(@PathVariable int user_id){
        return service.findById(user_id);
    }
    @PostMapping("/users")
    public User create(@RequestBody User user){
        return service.create(user);
    }
    
    @PutMapping("/users/{user_id}")
    public User assignRoles( @PathVariable int user_id, @RequestBody List<String> roles ){

        return service.assignRoles(user_id, roles);
    }
}
