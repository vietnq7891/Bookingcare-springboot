package com.bookingcare.security.entities;
import com.bookingcare.model.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private String roleId;
    private String phoneNumber;
    private String positionId;
    private String avatar;


//    @Column(nullable = false)
   @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;

//    @Column( nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
    private Date updatedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", referencedColumnName = "keyMap", insertable = false, updatable = false)
    private Allcode roleData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "positionId", referencedColumnName = "keyMap", insertable = false, updatable = false)
    private Allcode positionData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender", referencedColumnName = "keyMap", insertable = false, updatable = false)
    private Allcode genderData;


    @OneToOne(mappedBy = "user")
    private Markdown markdown;

    @OneToOne(mappedBy = "doctor")
    private DoctorInfor doctorInfo;

    @OneToMany(mappedBy = "doctorData")
    private List<Schedule> doctorData;

    @OneToMany(mappedBy = "patientData")
    private List<Booking> patientData;

}