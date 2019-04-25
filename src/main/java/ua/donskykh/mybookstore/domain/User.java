package ua.donskykh.mybookstore.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @NotBlank(message="Please enter your Name!")
    private String name;

    @Column(name = "surname")
    @NotBlank(message="Please enter your Surname!")
    private String surname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone")
    @NotBlank(message="Please enter your phone number!")
    private String phone;

    @Column(name = "role")
    private String role;

    @Column(name = "password")
    @NotBlank(message="Please enter your password!")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "enabled")
    private boolean enabled = true;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

    public User(@NotBlank(message = "Please enter your name!") String name, @NotBlank(message = "Please enter your surname!") String surname, String email, @NotBlank(message = "Please Enter your phone number!") String phone, String role, @NotBlank(message = "Please Enter your Password!") String password, String confirmPassword, boolean enabled, ShoppingCart shoppingCart) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.enabled = enabled;
        this.shoppingCart = shoppingCart;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surame) {
        this.surname = surame;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", name='" + name + ", surame='" + surname + ", email='" + email + ", phone='" + phone +
                ", role='" + role + ", password='" + password + ", enabled=" + enabled + ']';
    }
}
