package com.bookingcare.security.entities;
import com.bookingcare.model.entity.*;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    private String image;


   @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;


    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
    private Date updatedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", referencedColumnName = "keyMap", insertable = false, updatable = false)
    private Allcode roleData;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "positionId", referencedColumnName = "keyMap", insertable = false, updatable = false)
    @JsonManagedReference
    @JsonIgnore
    private Allcode positionData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender", referencedColumnName = "keyMap", insertable = false, updatable = false)
    @JsonManagedReference
    @JsonIgnore
    private Allcode genderData;


    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    @JsonIgnore
    private Markdown markdown;

    @OneToOne(mappedBy = "doctor",fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private DoctorInfor doctorInfor;

    @OneToMany(mappedBy = "doctorData",fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<Schedule> doctorData;

    @OneToMany(mappedBy = "patientData",fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<Booking> patientData;


}