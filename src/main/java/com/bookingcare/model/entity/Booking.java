package com.bookingcare.model.entity;

import com.bookingcare.security.entities.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "booking")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String statusId;


    private Integer doctorId;


    private Integer patientId;

    private String date;
    private String timeType;
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "createdAt")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "updatedAt")
    private Date updatedAt;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "patientId", referencedColumnName = "id",insertable = false, updatable = false)
    private User patientData;

    @ManyToOne
    @JoinColumn(name = "timeType", referencedColumnName = "keyMap",insertable = false, updatable = false)
    private Allcode timeTypeDataPatient;
}
