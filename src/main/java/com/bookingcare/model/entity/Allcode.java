package com.bookingcare.model.entity;

import com.bookingcare.security.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "allcodes")
public class Allcode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String keyMap;
    private String type;
    private String valueEn;
    private String valueVi;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    @OneToMany(mappedBy = "positionData")
    @JsonIgnore
    private List<User> usersByPosition;

    @OneToMany(mappedBy = "genderData")
    @JsonIgnore
    private List<User> usersByGender;

    @OneToMany(mappedBy = "timeTypeData")
    private List<Schedule> timeTypeData;

    @OneToMany(mappedBy = "priceTypeData")
    private List<DoctorInfor> priceTypeData;

    @OneToMany(mappedBy = "provinceTypeData")
    private List<DoctorInfor> provinceTypeData;

    @OneToMany(mappedBy = "paymentTypeData")
    private List<DoctorInfor> paymentTypeData;

    @OneToMany(mappedBy = "timeTypeDataPatient")
    private List<Booking> timeTypeDataPatient;
}
