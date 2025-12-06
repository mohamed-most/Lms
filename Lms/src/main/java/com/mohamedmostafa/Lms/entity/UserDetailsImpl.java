package com.mohamedmostafa.Lms.security;

import com.mohamedmostafa.Lms.entity.UserDetailsCustomized;
import com.mohamedmostafa.Lms.enums.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;


public class UserDetailsImpl implements UserDetailsCustomized {

    @Getter
    private final String email;
    private final String password;
    private final Role role;
    @Getter
    private final Integer id;

    public UserDetailsImpl(Integer id, String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    @Override
    public Integer getUserId() {
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
