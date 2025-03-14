package mis.projects.users.profiles.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mis.projects.users.profiles.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }
 
    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody Map<String, String> request ){
        String token = service.register(request.get("username"), request.get("password"));
        return ResponseEntity.ok( Map.of("token", token) );
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request ){
        String token = service.login(request.get("username"), request.get("password"));
        return ResponseEntity.ok(Map.of("token", token));
    }
}

