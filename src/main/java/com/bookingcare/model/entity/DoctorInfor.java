package com.bookingcare.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "doctor_infor")
public class DoctorInfor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long doctorId;

    private Long specialtyId;
    private Long clinicId;

    @Column(nullable = false)
    private String priceId;

    @Column(nullable = false)
    private String provinceId;

    @Column(nullable = false)
    private String paymentId;

    @Column(nullable = false)
    private String addressClinic;

    @Column(nullable = false)
    private String nameClinic;

    private String note;

    @Column(nullable = false)
    private Integer count;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "createdAt")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "updatedAt")
    private Date updatedAt;
}
