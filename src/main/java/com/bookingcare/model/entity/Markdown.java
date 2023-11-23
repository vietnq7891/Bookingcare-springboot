package com.bookingcare.model.entity;

import com.bookingcare.security.entities.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "markdown")
public class Markdown {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(nullable = false)
    private String contentHTML;

    @Lob
    @Column(nullable = false)
    private String contentMarkdown;

    @Lob
    private String description;

    private int doctorId;
    private int specialtyId;
    private int clinicId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "createdAt")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "updatedAt")
    private Date updatedAt;

    @OneToOne
    @JoinColumn(name = "doctorId",insertable = false, updatable = false)
    private User user;

}
