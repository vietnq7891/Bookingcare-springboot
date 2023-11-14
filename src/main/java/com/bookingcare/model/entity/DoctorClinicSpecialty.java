package com.bookingcare.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "doctor_clinic_specialty")
public class DoctorClinicSpecialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorId;
    private Long clinicId;
    private Long specialtyId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "createdAt")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "updatedAt")
    private Date updatedAt;

}
