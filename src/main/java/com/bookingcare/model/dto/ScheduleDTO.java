package com.bookingcare.model.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ScheduleDTO {
    private Integer id;
    private Integer currentNumber;
    private Integer maxNumber;
    private String date;
    private String timeType;
    private Integer doctorId;
    private Date createdAt;
    private Date updatedAt;
}
