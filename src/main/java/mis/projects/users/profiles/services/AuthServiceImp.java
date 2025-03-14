package mis.projects.users.profiles.services;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mis.projects.users.profiles.models.User;
import mis.projects.users.profiles.repositories.UserRepository;
import mis.projects.users.profiles.security.JwtUtil;

@Service
@AllArgsConstructor
@Primary
public class AuthServiceImp implements AuthService{

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;


    @Override
    public String register(String username, String password) {
        if( userRepo.findByUsername(username).isPresent() ){
            throw new RuntimeException("El usuario ya existe");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword( passwordEncoder.encode(password) );
    
        userRepo.save(user);
        return jwtUtil.generateToken(username);
    }

    @Override
    public String login(String username, String password) {
        Optional<User> userOpt = userRepo.findByUsername(username);
        if( userOpt.isEmpty() || passwordEncoder.matches(password, userOpt.get().getPassword()) ){
            throw new RuntimeException("Credenciales incorrectas");
        }
        return jwtUtil.generateToken(username);
    }
    
}
