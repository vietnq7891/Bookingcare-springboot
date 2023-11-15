package com.bookingcare.model.dto;

import com.bookingcare.model.entity.Allcodes;
import com.bookingcare.model.entity.User;
import lombok.Data;

import java.util.List;
@Data
public class UserResponse {
    private int errCode;
    private String errMessage;
    private User user;
    private List<User> users;
    private List<Allcodes> data;
}
