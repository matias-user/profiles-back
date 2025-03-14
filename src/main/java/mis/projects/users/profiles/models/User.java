package mis.projects.users.profiles.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "app_user" )
public class User {
    
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Id
    private int id;
    private String username;
    private String lastname;
    private String password;
    @Column( unique = true )
    @Pattern(
    regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,7}$",
    message = "El email ingresado no es válido")
    private String email;
    private int phoneNumber; 
    @ManyToMany( fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE } )
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
   
    public void setRoles(List<Role> roles) {
        this.roles.clear(); 
            this.roles.addAll(roles);
    }
   
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + username + ", lastname=" + lastname + ", email=" + email + ", phoneNumber="
                + phoneNumber + ", roles=" + roles + "]";
    }

    
}
