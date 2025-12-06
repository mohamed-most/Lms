package com.mohamedmostafa.Lms.security;

import com.mohamedmostafa.Lms.enums.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


public class UserDetailsImpl implements UserDetails {

    @Getter
    private final String email;
    private final String password;
    private final Role role;
    @Getter
    private final UUID id;

    public UserDetailsImpl(UUID id, String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.id = id;
    }


    public UUID getUserId() {
        return this.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
