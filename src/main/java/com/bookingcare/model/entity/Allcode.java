package com.bookingcare.model.entity;

import com.bookingcare.security.entities.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "allcodes")
public class Allcode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyMap;
    private String type;
    private String valueEn;
    private String valueVi;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "createdAt")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "updatedAt")
    private Date updatedAt;


    @OneToMany(mappedBy = "positionData")
    private List<User> positionData;

    @OneToMany(mappedBy = "genderData")
    private List<User> genderData;

//    @OneToMany(mappedBy = "timeTypeData")
//    private List<Schedule> timeTypeData;
//
//    @OneToMany(mappedBy = "priceTypeData")
//    private List<DoctorInfor> priceTypeData;
//
//    @OneToMany(mappedBy = "provinceTypeData")
//    private List<DoctorInfor> provinceTypeData;
//
//    @OneToMany(mappedBy = "paymentTypeData")
//    private List<DoctorInfor> paymentTypeData;
//
//    @OneToMany(mappedBy = "timeTypeDataPatient")
//    private List<Booking> timeTypeDataPatient;
}
