package com.bookingcare.model.dto;

import com.bookingcare.model.entity.Allcode;
import com.bookingcare.model.entity.DoctorInfor;
import com.bookingcare.model.entity.Markdown;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private String roleId;
    private String phoneNumber;
    private String positionId;
    private String image;
    private Date createdAt;
    private Date updatedAt;
    private MarkdownDTO markdown;
    private DoctorInforDTO doctorInfor;
    private Allcode positionData;


    public UserDTO(Integer id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
