package com.bookingcare.model.entity;

import com.bookingcare.security.entities.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "schedule")
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
    private User doctorData;

}
