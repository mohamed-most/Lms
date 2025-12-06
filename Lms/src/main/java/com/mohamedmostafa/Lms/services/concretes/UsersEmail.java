package com.mohamedmostafa.Lms.services.concretes;


import com.mohamedmostafa.Lms.entity.User;
import com.mohamedmostafa.Lms.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UsersEmailUtil {


    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public UsersEmailUtil(UserRepo userRepo, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    public void createUserAndSendPasswordEmail(String email, String username) {
        if (userRepo.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = User.builder()
                .email(email).username(username).build();

        // Generate token
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setTokenExpiry(LocalDateTime.now().plusHours(24)); // token valid 24h

        userRepo.save(user);

        // Send email
        String setPasswordUrl = "http://localhost:8080/api/v1/users/set-password?token=" + token;
        String html = "<h3>Welcome to LMS</h3>" +
                "<p>Click the link below to set your password:</p>" +
                "<a href=\"" + setPasswordUrl + "\">Set Password</a>";

        emailService.sendEmail(user.getEmail(), "Set Your Password", html);
    }


}
