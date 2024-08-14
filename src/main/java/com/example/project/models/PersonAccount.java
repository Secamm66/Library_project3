package com.example.project.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person_account")
public class PersonAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Введите username")
    @Size(min = 3, max = 100, message = "username может содержать от 3 до 100 символов")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Введите пароль")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$",
            message = "Пароль должен содержать минимум 8 символов, " +
                    "включая хотя бы одну цифру, одну строчную букву, " +
                    "одну заглавную букву и один специальный символ (!@#$%^&*).")
    @Transient
    private String password;

    @Column(name = "password")
    private String passwordEncoded;

    @NotEmpty(message = "Введите e-mail")
    @Email(message = "Неверно указан e-mail")
    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "role")
    private String role;

    @Transient
    private boolean isAdmin;

    public PersonAccount(String username, String password, String email, Person person) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.person = person;
    }

    public PersonAccount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordEncoded() {
        return passwordEncoded;
    }

    public void setPasswordEncoded(String passwordEncoded) {
        this.passwordEncoded = passwordEncoded;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "PersonAccount{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", person=" + person +
                '}';
    }
}
