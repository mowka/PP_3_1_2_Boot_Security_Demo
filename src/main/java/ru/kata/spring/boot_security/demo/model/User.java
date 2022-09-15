package ru.kata.spring.boot_security.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private int age;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public String getRolesInfo() {
        return roles.stream().map(Role::getName).map(r -> r.substring(5)).toList().toString();
    }

}
