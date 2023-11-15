package com.bookingcare.model.dto;

import com.bookingcare.model.entity.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserRequest {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private String phoneNumber;
    private String positionId;
    private String image;
    private Set<Role> roleIds;
}
