package mis.projects.users.profiles.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
@Table( name = "app_user" )
public class User {
    
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Id
    private int id;
    private String name;
    private String lastname;
    @Column( unique = true )
    @Pattern(
    regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,7}$",
    message = "El email ingresado no es válido")
    private String email;
    @Min(8)
    @Max(8)
    private int phoneNumber; 
    @OneToMany( mappedBy = "user", cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE } )
    @JsonManagedReference
    private List<Role> roles;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", lastname=" + lastname + ", email=" + email + ", phoneNumber="
                + phoneNumber + ", roles=" + roles + "]";
    }

    
}
