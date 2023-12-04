package com.bookingcare.model.dto;

import lombok.Data;

import java.util.Date;
@Data
public class SpecialtyDTO {
    private Integer id;
    private byte[] image;
    private String name;
    private String descriptionHTML;
    private String descriptionMarkdown;

    private Date createdAt;
    private Date updatedAt;
}
