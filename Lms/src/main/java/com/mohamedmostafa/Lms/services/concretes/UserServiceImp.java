package com.mohamedmostafa.Lms.services.concretes;

import com.mohamedmostafa.Lms.dtos.response.UserResponseDto;
import com.mohamedmostafa.Lms.entity.User;
import com.mohamedmostafa.Lms.entity.UserDetailsCustomized;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.mappers.UserMapper;
import com.mohamedmostafa.Lms.repositories.UserRepo;
import com.mohamedmostafa.Lms.services.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private UserRepo userRepo;

    @Autowired
    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public UserResponseDto getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsCustomized userDetails = (UserDetailsCustomized) auth.getPrincipal();

        User user = userRepo.findById(userDetails.getUserId())
                .orElseThrow(() -> new ResourceNotFoundEx("User not found"));

        return UserMapper.toResponseDto(user);
    }
}
