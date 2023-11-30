package com.bookingcare.model.dto;

import com.bookingcare.model.entity.DoctorInfor;
import com.bookingcare.model.entity.Markdown;
import lombok.Data;

@Data

public class DoctorDTO {
    private String contentHTML;
    private String contentMarkdown;
    private String description;
    private Integer doctorId;
    private String action;

    private String selectedPrice;
    private String selectedPayment;
    private String selectedProvince;
    private String nameClinic;
    private String addressClinic;
    private String note;
    private Integer clinicId;
    private Integer specialtyId;
    private Integer count;
}