package com.bookingcare.model.entity;

import com.bookingcare.security.entities.User;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "markdown")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
    @Column(nullable = false)
    private Date createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
    @Column(nullable = false)
    private Date updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId",referencedColumnName = "id",insertable = false, updatable = false)
    @JsonManagedReference("userRef")
    private User user;

}
