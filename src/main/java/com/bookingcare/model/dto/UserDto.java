package com.bookingcare.model.dto;

import com.bookingcare.security.entities.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class UserDto {

    private String avatar;
    private MultipartFile avatarFile;

    // Các trường khác của UserDto

    public static UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setAvatar(user.getAvatar());
        return userDto;
    }
}
