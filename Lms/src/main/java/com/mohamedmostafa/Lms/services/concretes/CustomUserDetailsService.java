package com.mohamedmostafa.Lms.services.concretes;

import com.mohamedmostafa.Lms.entity.User;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.repositories.UserRepo;
import com.mohamedmostafa.Lms.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static com.mohamedmostafa.Lms.messages.ControllersMessages.EMAIL_NOT_FOUND_MSG;


@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws ResourceNotFoundEx {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundEx(EMAIL_NOT_FOUND_MSG));

        return new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
