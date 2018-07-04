package com.codecool.library.controller.admin;

import com.codecool.library.model.Admin;
import com.codecool.library.repository.AdminRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    static class RegisterNewAdminData {

        String username;

        String password;
        @JsonProperty("password-again")
        String passwordAgain;

        public RegisterNewAdminData() {
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

        public String getPasswordAgain() {
            return passwordAgain;
        }

        public void setPasswordAgain(String passwordAgain) {
            this.passwordAgain = passwordAgain;
        }

        boolean isPasswordRepeatedCorrectly() {
            return password.equals(passwordAgain);
        }
    }

    private final
    AdminRepository adminRepository;

    private final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register-new-admin")
    ResponseEntity<String> registerNewAdmin(@RequestBody RegisterNewAdminData registerNewAdminData) {
        Optional<Admin> adminOptional = adminRepository.findByUsername(registerNewAdminData.getUsername());

        if(adminOptional.isPresent())
            return new ResponseEntity<>("There is already an admin with this username", HttpStatus.BAD_REQUEST);

        if(!registerNewAdminData.isPasswordRepeatedCorrectly())
            return new ResponseEntity<>("Password repeated incorrectly.", HttpStatus.BAD_REQUEST);

        Admin newAdmin = new Admin(registerNewAdminData.getUsername(), bCryptPasswordEncoder.encode(registerNewAdminData.getPassword()), false);

        adminRepository.save(newAdmin);

        return new ResponseEntity<>("New admin successfully created.", HttpStatus.OK);
    }

    @PostMapping("/change-password")
    ResponseEntity<String> updatePassword(@RequestBody PasswordChangeData passwordChangeData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Admin> adminOptional = adminRepository.findByUsername(authentication.getName());

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

        return new ResponseEntity<>("Password changed successfully.", HttpStatus.OK);
    }
}
