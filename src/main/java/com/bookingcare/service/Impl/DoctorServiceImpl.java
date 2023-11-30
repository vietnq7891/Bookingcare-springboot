package com.bookingcare.service.Impl;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.common.ValidationResult;
import com.bookingcare.exception.BaseException;
import com.bookingcare.model.dto.DoctorDTO;
import com.bookingcare.model.entity.Markdown;
import com.bookingcare.repository.MarkdownRepository;
import com.bookingcare.repository.SpecialtyRepository;
import com.bookingcare.security.entities.User;
import com.bookingcare.security.repo.IUserRepository;
import com.bookingcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {


    @Autowired
    IUserRepository userRepository;
    @Autowired
    SpecialtyRepository specialtyRepository;

    @Autowired
    MarkdownRepository markdownRepository;

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

//    @Transactional
//    public ApiResponse saveDetailInforDoctor(DoctorDTO doctorDTO) {
//        try {
//            // Upsert to Markdown
//            if ("CREATE".equals(doctorDTO.getAction())) {
//                Markdown markdown = new Markdown();
//                setMarkdownFields(markdown, doctorDTO);
//                markdownRepository.save(markdown);
//            } else if ("EDIT".equals(doctorDTO.getAction())) {
//                Optional<Markdown> optionalMarkdown = markdownRepository.findByDoctorId(doctorDTO.getDoctorId());
//                optionalMarkdown.ifPresent(markdown -> {
//                    setMarkdownFields(markdown, doctorDTO);
//                    markdownRepository.save(markdown);
//                });
//            }
//
//            // Upsert to Doctor Info
//            Optional<DoctorInfor> optionalDoctorInfor = doctorInforRepository.findByDoctorId(doctorDTO.getDoctorId());
//            if (optionalDoctorInfor.isPresent()) {
//                DoctorInfor doctorInfor = optionalDoctorInfor.get();
//                setDoctorInforFields(doctorInfor, doctorDTO);
//                doctorInforRepository.save(doctorInfor);
//            } else {
//                DoctorInfor doctorInfor = new DoctorInfor();
//                setDoctorInforFields(doctorInfor, doctorDTO);
//                doctorInforRepository.save(doctorInfor);
//            }
//
//            return new ApiResponse(0, "Save doctor information successfully!");
//        } catch (Exception e) {
//            throw new BaseException("Undefined error: " + e.getMessage());
//        }
//    }
//
//    private void setMarkdownFields(Markdown markdown, DoctorDTO doctorDTO) {
//        markdown.setContentHTML(doctorDTO.getContentHTML());
//        markdown.setContentMarkdown(doctorDTO.getContentMarkdown());
//        markdown.setDescription(doctorDTO.getDescription());
//        markdown.setDoctorId(doctorDTO.getDoctorId());
//    }
//
//    private void setDoctorInforFields(DoctorInfor doctorInfor, DoctorDTO doctorDTO) {
//        doctorInfor.setDoctorId(doctorDTO.getDoctorId());
//        doctorInfor.setPriceId(doctorDTO.getSelectedPrice());
//        doctorInfor.setProvinceId(doctorDTO.getSelectedProvince());
//        doctorInfor.setPaymentId(doctorDTO.getSelectedPayment());
//        doctorInfor.setNameClinic(doctorDTO.getNameClinic());
//        doctorInfor.setAddressClinic(doctorDTO.getAddressClinic());
//        doctorInfor.setNote(doctorDTO.getNote());
//        doctorInfor.setSpecialtyId(doctorDTO.getSpecialtyId());
//        doctorInfor.setClinicId(doctorDTO.getClinicId());
//    }

}

