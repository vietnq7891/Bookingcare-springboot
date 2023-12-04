package com.bookingcare.model.dto;

import lombok.Data;

import java.util.Date;
@Data
public class MarkdownDTO {
    private Integer id;
    private String contentHTML;
    private String contentMarkdown;
    private String description;
    private int doctorId;
    private int specialtyId;
    private int clinicId;

    private Date createdAt;

    private Date updatedAt;
}
