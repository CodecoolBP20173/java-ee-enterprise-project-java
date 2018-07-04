package com.codecool.library.service;

import com.codecool.library.model.Admin;
import com.codecool.library.repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {

    public InitializerBean(AdminRepository adminRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin("admin", bCryptPasswordEncoder.encode("admin"), true);
            adminRepository.save(admin);
        }
    }
}
