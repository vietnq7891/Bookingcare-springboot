package com.bookingcare.model.dto;

import lombok.Data;

import java.util.Date;
@Data
public class DoctorClinicSpecialtyDTO {
    private Integer id;
    private Integer doctorId;
    private Integer clinicId;
    private Integer specialtyId;
    private Date createdAt;
    private Date updatedAt;
}
