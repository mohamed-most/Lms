package com.mohamedmostafa.Lms.services.concretes;


import com.mohamedmostafa.Lms.dtos.request.LoginRequestDto;
import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.entity.User;
import com.mohamedmostafa.Lms.entity.UserDetailsCustomized;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.repositories.UserRepo;
import com.mohamedmostafa.Lms.security.UserDetailsImpl;
import com.mohamedmostafa.Lms.services.abstracts.AuthService;
import com.mohamedmostafa.Lms.utils.JwtUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Primary
@Service
public class AuthServiceImp implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImp(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(@RequestBody LoginRequestDto loginDto) {
        // 1️⃣ Find user by email
        User user = userRepo.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundEx("Email not found"));

        // 2️⃣ Validate password
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials"); // you can create a custom exception
        }
        // 3️⃣ Convert User to UserDetails
        UserDetailsCustomized userDetails = new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole() // make sure your UserDetailsImpl accepts enum Role
        );
        // 3️⃣ Generate JWT token
        String token = jwtUtil.generateToken(userDetails);

        // 4️⃣ Prepare response map
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("email", user.getEmail());
        data.put("role", user.getRole());
        data.put("token", token);

        // 5️⃣ Wrap in ApiResponse
        ApiResponse<Map<String, Object>> response = ApiResponse.<Map<String, Object>>builder()
                .success(true)
                .data(data)
                .build();

        return ResponseEntity.ok(response);
    }
}

