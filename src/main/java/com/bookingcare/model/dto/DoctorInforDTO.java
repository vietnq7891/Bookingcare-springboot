package com.bookingcare.model.dto;
import com.bookingcare.model.entity.Allcode;
import lombok.Data;

import java.util.Date;
@Data
public class DoctorInforDTO {
    private Integer id;
    private Integer doctorId;
//    private Integer specialtyId;
//    private Integer clinicId;
    private String priceId;
    private String provinceId;
    private String paymentId;
    private String addressClinic;
    private String nameClinic;
    private String note;
    private Integer count;
    private Date createdAt;
    private Date updatedAt;
    private Allcode priceTypeData;
    private Allcode provinceTypeData;
    private Allcode paymentTypeData;

}
