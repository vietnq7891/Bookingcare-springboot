package com.bookingcare.model.dto;

import com.bookingcare.model.entity.Allcode;
import com.bookingcare.security.entities.User;
import lombok.Data;

import java.util.Date;
@Data
public class BookingDTO {
        private Integer id;
        private  String email;
        private String selectedGender;
        private String address;
        private String fullName;
        private String phoneNumber;
        private String statusId;
        private Integer doctorId;
        private Integer patientId;
        private String date;
        private String timeType;
        private String token;
        private Date createdAt;
        private Date updatedAt;
        private User patientData;
//        private Allcode timeTypeDataPatient;
}
