package com.task_springboot.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private byte age;
    private String login;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User(String name, String lastname, byte age, String login) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.login = login;
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

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

}