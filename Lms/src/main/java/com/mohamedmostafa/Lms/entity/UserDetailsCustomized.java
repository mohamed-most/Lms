package com.mohamedmostafa.Lms.entity;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsCustomized extends UserDetails {
    public Integer getUserId();

}
