package com.bookingcare.model.entity;

import com.bookingcare.security.entities.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "doctor_infor")
public class DoctorInfor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer doctorId;

    private Integer specialtyId;
    private Integer clinicId;

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

     @OneToOne
     @JoinColumn(name = "doctorId",insertable = false, updatable = false)
     private User doctor;

     @ManyToOne
     @JoinColumn(name = "priceId", referencedColumnName = "keyMap",insertable = false, updatable = false)
     private Allcode priceTypeData;

     @ManyToOne
     @JoinColumn(name = "provinceId", referencedColumnName = "keyMap",insertable = false, updatable = false)
     private Allcode provinceTypeData;

     @ManyToOne
     @JoinColumn(name = "paymentId", referencedColumnName = "keyMap",insertable = false, updatable = false)
     private Allcode paymentTypeData;
}
