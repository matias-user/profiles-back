package mis.projects.users.profiles.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mis.projects.users.profiles.models.Role;
import mis.projects.users.profiles.services.RoleService;

@RestController
@RequestMapping("/api")
public class RoleController {
    private RoleService service;

    public RoleController(@Qualifier("roleServiceImp") RoleService service) {
        this.service = service;
    }
    
    @PostMapping("/roles")
    public Role createRole(@RequestBody Role role){
        return service.create(role);
    }
}
