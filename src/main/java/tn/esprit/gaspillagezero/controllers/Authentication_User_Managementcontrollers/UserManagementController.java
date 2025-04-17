package tn.esprit.gaspillagezero.controllers.Authentication_User_Managementcontrollers;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.dto.ReqRes;
import tn.esprit.gaspillagezero.entites.Authentication_User_Management.User;
import tn.esprit.gaspillagezero.repository.Authentication_User_Management_Repository.UserRepo;
import tn.esprit.gaspillagezero.services.Authentication_User_Management_Service.JWTUtils;
import tn.esprit.gaspillagezero.services.Authentication_User_Management_Service.Mailing.EmailService;
import tn.esprit.gaspillagezero.services.Authentication_User_Management_Service.UserManagementServices;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserManagementController {
    private final EmailService emailService;
    @Autowired
    private UserManagementServices userManagementServices;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserManagementController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> regeister(@RequestBody ReqRes reg){
        return ResponseEntity.ok(userManagementServices.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(userManagementServices.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(userManagementServices.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(userManagementServices.getAllUsers());

    }

    @GetMapping("/admin/get-user/{userId}")
    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(userManagementServices.getUserById(userId));

    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody User reqres){
        return ResponseEntity.ok(userManagementServices.updateUser(userId, reqres));
    }

    @PostMapping("/admin/create")
    public ResponseEntity<ReqRes> createUser(@RequestBody ReqRes reg){
        return ResponseEntity.ok(userManagementServices.createUser(reg));
    }

    /*@GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = userManagementServices.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }*/

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer userId){
        return ResponseEntity.ok(userManagementServices.deleteUser(userId));
    }

    /*@PostMapping("/send")
    public String sendTestEmail() {
        try {
            String to = "mannaiomar28@gmail.com";
            String username = "mannai omar";
            String confirmationUrl = "http://localhost:8080/confirm";
            String activationCode = "123456";
            String subject = "Welcome to GrowthNest!";

            emailService.sendEmail(to, username, confirmationUrl, activationCode, subject);
            return "✅ Email sent successfully to: " + to;
        } catch (MessagingException e) {
            e.printStackTrace();
            return "❌ Failed to send email: " + e.getMessage();
        }
    }*/

    @GetMapping("/auth/verify")
    public String verifyEmail(@RequestParam String token) {
        return userRepo.findByVerificationToken(token).map(user -> {
            user.setEnabled(true);
            user.setVerificationToken(null);
            userRepo.save(user);
            return "✅ Account verified successfully!";
        }).orElse("❌ Invalid or expired token.");
    }

    @PostMapping("/auth/google-login")
    public ResponseEntity<?> loginWithGoogle(@RequestBody Map<String, String> body) {
        Map<String, Object> response = userManagementServices.loginWithGoogle(body.get("idToken"));

        if (response.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> body) throws MessagingException {
        String email = body.get("email");

        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user with this email.");
        }

        String token = jwtUtils.generatePasswordResetToken(email);
        String resetUrl = "http://localhost:4201/reset-password?token=" + token;

        emailService.sendEmail2(email, email,  resetUrl,"Password Reset Request"
                );

        return ResponseEntity.ok(Map.of("message", "✅ Password reset link sent!"));

    }

    @PostMapping("/auth/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        String newPassword = body.get("newPassword"); // ← Fix here

        try {
            String email = jwtUtils.extractUsername(token);
            Optional<User> userOptional = userRepo.findByEmail(email);

            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid token");
            }

            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepo.save(user);

            return ResponseEntity.ok("✅ Password reset successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Invalid or expired token");
        }
    }


}