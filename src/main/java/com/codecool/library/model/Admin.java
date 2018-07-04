package com.codecool.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Admin extends BaseModel {

    public enum Authorities {
        ADMIN, SUPERVISOR;
    }

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    private String password;

    private boolean supervisor;

    public Admin() {
    }

    public Admin(String username, String password, boolean supervisor) {
        this.username = username;
        this.password = password;
        this.supervisor = supervisor;
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

    public boolean isSupervisor() {
        return supervisor;
    }

    public void setSupervisor(boolean supervisor) {
        this.supervisor = supervisor;
    }

    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(Authorities.ADMIN.toString()));

        if(isSupervisor())
            authorities.add(new SimpleGrantedAuthority(Authorities.SUPERVISOR.toString()));

        return authorities;
    }
}


