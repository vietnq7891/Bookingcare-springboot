package com.bookingcare.security.jwt;

import com.bookingcare.model.dto.UserDTO;
import com.bookingcare.security.entities.User;
import lombok.Data;

@Data

public class JwtResponse {
//    private Integer id;
    private String token;
    private String type = "Bearer";
    private User user;
//    private String email;
//    private Collection<? extends GrantedAuthority> roles;


    public JwtResponse(String accessToken, User user ) {
        this.token = accessToken;
        this.user = user;


    }

}
