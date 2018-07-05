package com.codecool.library.config;

import com.codecool.library.model.Admin;
import com.codecool.library.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Optional;

@Component
public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = repository.findByUsername(username);
        if (admin.isPresent()) {
            Admin adminObj = admin.get();
            return new User(
                    adminObj.getUsername(),
                    adminObj.getPassword(),
                    adminObj.getAuthorities());
        }
        return null;
    }
}
