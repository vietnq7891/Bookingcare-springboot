package com.bookingcare.model.entity;

import com.bookingcare.security.entities.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "schedule")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer currentNumber;
    private Integer maxNumber;
    private String date;
    private String timeType;
    private Integer doctorId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "createdAt")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "updatedAt")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "timeType", referencedColumnName = "keyMap",insertable = false, updatable = false)
    private Allcode timeTypeData;

    @ManyToOne
    @JoinColumn(name = "doctorId", referencedColumnName = "id",insertable = false, updatable = false)
    @JsonBackReference
    private User doctorData;

}
