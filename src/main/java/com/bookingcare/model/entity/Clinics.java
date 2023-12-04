package com.bookingcare.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "clinics")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Clinics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;
    private String name;

    @Lob
    private String descriptionMarkdown;

    @Lob
    private String descriptionHTML;

    @Lob
    private byte[] image;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "createdAt")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "updatedAt")
    private Date updatedAt;
}
