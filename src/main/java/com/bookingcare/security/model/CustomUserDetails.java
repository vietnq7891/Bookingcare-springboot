package com.bookingcare.security.model;

import com.bookingcare.model.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Data
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> roles;

    public CustomUserDetails(String email, String password, Collection<? extends GrantedAuthority> roles) {
        this.username = email;
        this.password = password;
        this.roles = roles;
    }


    public static CustomUserDetails build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());

        return new CustomUserDetails(
                user.getPassword(),
                user.getEmail(),
                authorities
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
