package com.bookingcare.security.jwt;

import com.bookingcare.exception.ErrorMessage;
import com.bookingcare.security.entities.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Data

public class JwtResponse {
    private Integer id;
    private String token;
    private String type = "Bearer";
    private String username;
    private String email;
    private Collection<? extends GrantedAuthority> roles;


    public JwtResponse(String accessToken, Integer id, String username, String email, Collection<? extends GrantedAuthority> roles ) {
        this.token = accessToken;
        this.username = username;
        this.roles = roles;
        this.email = email;
        this.id = id;


    }

}
