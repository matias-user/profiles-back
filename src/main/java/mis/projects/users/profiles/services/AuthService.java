package mis.projects.users.profiles.services;

public interface AuthService {
    public String register(String username, String password);
    public String login(String username, String password);
}
