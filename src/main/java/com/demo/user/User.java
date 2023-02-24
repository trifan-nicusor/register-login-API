package com.demo.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements org.springframework.security.core.userdetails.UserDetails {
    @Id
    private int id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private boolean isLocked;
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}