package com.codecool.library.controller.admin;

import com.codecool.library.model.Admin;
import com.codecool.library.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminApi {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/change-password")
    ResponseEntity<String> updatePassword(Principal principal,
                        @RequestParam("old-password") String oldPassword,
                        @RequestParam("new-password") String newPassword,
                        @RequestParam("new-password-again") String newPasswordAgain) {
        Optional<Admin> admin = adminRepository.findByUsername(principal.getName());

        if(!admin.isPresent())
            return new ResponseEntity<String>("User not logged in", HttpStatus.UNAUTHORIZED);

        if(!newPassword.equals(newPasswordAgain))
            return new ResponseEntity<String>("New password repeated incorrectly.", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<String>("", HttpStatus.OK);
    }
}
