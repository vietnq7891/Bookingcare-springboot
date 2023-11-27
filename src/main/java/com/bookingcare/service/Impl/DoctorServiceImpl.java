package com.bookingcare.service.Impl;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.common.ValidationResult;
import com.bookingcare.exception.BaseException;
import com.bookingcare.model.entity.DoctorInfor;
import com.bookingcare.model.entity.Markdown;
import com.bookingcare.repository.DoctorInforRepository;
import com.bookingcare.repository.MarkdownRepository;
import com.bookingcare.repository.SpecialtyRepository;
import com.bookingcare.security.entities.User;
import com.bookingcare.security.repo.IUserRepository;
import com.bookingcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private MarkdownRepository markdownRepository;

    @Autowired
    private DoctorInforRepository doctorInforRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    SpecialtyRepository specialtyRepository;
    private void hidePasswords(List<User> users) {
        for (User user : users) {
            user.setPassword(null);
        }
    }
    @Override
    public ApiResponse getTopDoctorHome(int limit) {
        try {
            List<User> users = userRepository.findTopDoctors(limit);
            hidePasswords(users);

            // You can process attributes, filters, and format data here if needed

            return new ApiResponse(0, "Success", users);
        } catch (Exception e) {
            // Handle exceptions
            return new ApiResponse(-1, "Error from server...", null);
        }
    }
    @Override
    public ApiResponse<List<User>> getAllDoctors() {
        try {
            List<User> doctors = userRepository.findByRoleId("R2");

            // Exclude password and image fields
            doctors.forEach(doctor -> {
                doctor.setPassword(null);
                doctor.setAvatar(null);
            });

            return new ApiResponse<>(0,"Success", doctors);
        } catch (Exception e) {
            throw new BaseException(500, "An error occurred while fetching doctors");
        }
    }

//    @Override
//    public ValidationResult checkRequiredFields(DoctorInfor doctorInfor) {
//        String[] arrFields = {"doctorId", "contentHTML", "contentMarkdown", "action",
//                "selectedPrice", "selectedPayment", "selectedProvince", "nameClinic",
//                "addressClinic", "note", "specialtyId"
//        };
//
//        boolean isValid = true;
//        String missingField = "";
//
//        for (String field : arrFields) {
//            if (getFieldValue(doctorInfor, field) == null) {
//                isValid = false;
//                missingField = field;
//                break;
//            }
//        }
//
//        return new ValidationResult(isValid, missingField);
//    }
//
//    private Object getFieldValue(DoctorInfor doctorInfor, String fieldName) {
//        // Sử dụng reflection để lấy giá trị của trường trong đối tượng DoctorInfor
//        try {
//            Field field = DoctorInfor.class.getDeclaredField(fieldName);
//            field.setAccessible(true);
//            return field.get(doctorInfor);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public ApiResponse<String> saveDetailInforDoctor(DoctorInfor doctorInfor) {
//        try {
//            // Kiểm tra các trường bắt buộc
//            ValidationResult checkResult = checkRequiredFields(doctorInfor);
//            if (!checkResult.isValid()) {
//                return new ApiResponse<>(1, "Missing parameter: " + checkResult.getElement(), null);
//            }
//
//            // Upsert thông tin vào Markdown
//            if ("CREATE".equals(doctorInfor.g)) {
//                Markdown markdown = new Markdown();
//                markdown.setContentHTML(markdown.getContentHTML());
//                markdown.setContentMarkdown(markdown.getContentMarkdown());
//                markdown.setDescription(markdown.getDescription());
//                markdown.setDoctorId(markdown.getDoctorId());
//                markdownRepository.save(markdown);
//            } else if ("EDIT".equals(doctorInfor.getAction())) {
//                Optional<Markdown> optionalMarkdown = markdownRepository.findByDoctorId(doctorInfor.getDoctorId());
//                if (optionalMarkdown.isPresent()) {
//                    Markdown existingMarkdown = optionalMarkdown.get();
//                    existingMarkdown.setContentHTML(doctorInfor.getContentHTML());
//                    existingMarkdown.setContentMarkdown(doctorInfor.getContentMarkdown());
//                    existingMarkdown.setDescription(doctorInfor.getDescription());
//                    existingMarkdown.setUpdateAt(new Date());
//                    markdownRepository.save(existingMarkdown);
//                }
//            }
//
//            // Upsert thông tin vào DoctorInfor
//            Optional<DoctorInfor> optionalDoctorInfor = doctorInforRepository.findByDoctorId(doctorInfor.getDoctorId());
//            if (optionalDoctorInfor.isPresent()) {
//                DoctorInfor existingDoctorInfor = optionalDoctorInfor.get();
//                updateDoctorInfor(existingDoctorInfor, doctorInfor);
//                doctorInforRepository.save(existingDoctorInfor);
//            } else {
//                DoctorInfor newDoctorInfor = new DoctorInfor();
//                updateDoctorInfor(newDoctorInfor, doctorInfor);
//                doctorInforRepository.save(newDoctorInfor);
//            }
//
//            return new ApiResponse<>(0, "Save doctor information succeeded!", null);
//        } catch (Exception e) {
//            throw new BaseException(500, "An error occurred while saving doctor information");
//        }
//    }
//
//    private void updateDoctorInfor(DoctorInfor existingDoctorInfor, DoctorInfor newDoctorInfor) {
//        existingDoctorInfor.setPriceId(newDoctorInfor.getSelectedPrice());
//        existingDoctorInfor.setProvinceId(newDoctorInfor.getSelectedProvince());
//        existingDoctorInfor.setPaymentId(newDoctorInfor.getSelectedPayment());
//        existingDoctorInfor.setNameClinic(newDoctorInfor.getNameClinic());
//        existingDoctorInfor.setAddressClinic(newDoctorInfor.getAddressClinic());
//        existingDoctorInfor.setNote(newDoctorInfor.getNote());
//        existingDoctorInfor.setSpecialtyId(newDoctorInfor.getSpecialtyId());
//        existingDoctorInfor.setClinicId(newDoctorInfor.getClinicId());
//    }

}


