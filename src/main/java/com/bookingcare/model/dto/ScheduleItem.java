package com.bookingcare.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ScheduleItem {
    private List<ScheduleDTO> arrSchedule;
    private Integer doctorId;
    private String formatedDate;


}
