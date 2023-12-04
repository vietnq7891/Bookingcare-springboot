package com.bookingcare.model.dto;

import lombok.Data;

import java.util.Date;
@Data
public class HistoryDTO {
    private Integer id;
    private Integer patientId;
    private Integer doctorId;
    private String description;
    private String files;
    private Date createdAt;
    private Date updatedAt;

}
