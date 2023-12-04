package com.bookingcare.model.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ClinicsDTO {
    private Integer id;
    private String address;
    private String name;
    private String descriptionMarkdown;
    private String descriptionHTML;
    private byte[] image;
    private Date createdAt;
    private Date updatedAt;
}
