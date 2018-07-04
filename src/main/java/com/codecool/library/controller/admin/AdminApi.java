package com.codecool.library.controller.admin;

import com.codecool.library.model.Admin;
import com.codecool.library.repository.AdminRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminApi {

    @Autowired
    public AdminApi(AdminRepository adminRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminRepository = adminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    static class PasswordChangeData {
        @JsonProperty("old-password")
        String oldPassword;
        @JsonProperty("new-password")
        String newPassword;
        @JsonProperty("new-password-again")
        String newPasswordAgain;

        public PasswordChangeData() {
        }

        boolean repeatedCorrectly() {
            return newPassword.equals(newPasswordAgain);
        }

        boolean sameAsOld(){
            return newPassword.equals(oldPassword);
        }

        String getOldPassword() {
            return oldPassword;
        }

        String getNewPassword() {
            return newPassword;
        }

    }

    private final
    AdminRepository adminRepository;

    private final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/change-password")
    ResponseEntity<String> updatePassword(Principal principal, @RequestBody PasswordChangeData passwordChangeData) {
        Optional<Admin> adminOptional = adminRepository.findByUsername(principal.getName());

        if(!adminOptional.isPresent())
            return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);

        Admin admin = adminOptional.get();

        if(!passwordChangeData.repeatedCorrectly())
            return new ResponseEntity<>("New password repeated incorrectly.", HttpStatus.BAD_REQUEST);

        if(passwordChangeData.sameAsOld())
            return new ResponseEntity<>("New password is identical to the old one.", HttpStatus.BAD_REQUEST);

        if(!bCryptPasswordEncoder.matches(passwordChangeData.getOldPassword(), admin.getPassword()))
            return new ResponseEntity<>("Incorrect password entered.", HttpStatus.UNAUTHORIZED);

        admin.setPassword(bCryptPasswordEncoder.encode(passwordChangeData.getNewPassword()));

        adminRepository.save(admin);

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
