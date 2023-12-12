package com.bookingcare.model.entity;

import com.bookingcare.security.entities.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "allcodes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @JsonBackReference
    private List<User> usersByPosition;

    @OneToMany(mappedBy = "genderData")
    @JsonBackReference
    private List<User> usersByGender;

    @OneToMany(mappedBy = "timeTypeData")
    @JsonBackReference
    private List<Schedule> timeTypeData;

    @OneToMany(mappedBy = "priceTypeData")
    @JsonBackReference
    private List<DoctorInfor> priceTypeData;

    @OneToMany(mappedBy = "provinceTypeData")
    @JsonBackReference
    private List<DoctorInfor> provinceTypeData;

    @OneToMany(mappedBy = "paymentTypeData")
    @JsonBackReference
    private List<DoctorInfor> paymentTypeData;

    @OneToMany(mappedBy = "timeTypeDataPatient")
    @JsonBackReference
    private List<Booking> timeTypeDataPatient;
}
