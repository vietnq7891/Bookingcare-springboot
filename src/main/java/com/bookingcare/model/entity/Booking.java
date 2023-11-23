package com.bookingcare.model.entity;

import com.bookingcare.security.entities.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String statusId;

    @Column(name = "doctorId")
    private Long doctorId;

    @Column(name = "patientId")
    private Long patientId;

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
    @JoinColumn(name = "patientId", referencedColumnName = "id")
    private User patientData;

    @ManyToOne
    @JoinColumn(name = "timeType", referencedColumnName = "keyMap")
    private Allcode timeTypeDataPatient;
}
