package com.bookingcare.model.entity;

import com.bookingcare.security.entities.User;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "doctor_infor")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

//    @Column(nullable = false)
    private String nameClinic;

    private String note;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer count;

    public DoctorInfor() {
        this.count = 0;
    }

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
    @Column(nullable = false)
    private Date createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
    @Column(nullable = false)
    private Date updatedAt;

     @OneToOne
     @JsonBackReference
     @JoinColumn(name = "doctorId",insertable = false, updatable = false)
     private User doctor;

     @ManyToOne
     @JoinColumn(name = "priceId", referencedColumnName = "keyMap",insertable = false, updatable = false)
     @JsonManagedReference
     private Allcode priceTypeData;

     @ManyToOne
     @JoinColumn(name = "provinceId", referencedColumnName = "keyMap",insertable = false, updatable = false)
     @JsonManagedReference
     private Allcode provinceTypeData;

     @ManyToOne
     @JoinColumn(name = "paymentId", referencedColumnName = "keyMap",insertable = false, updatable = false)
     @JsonManagedReference
     private Allcode paymentTypeData;
}
