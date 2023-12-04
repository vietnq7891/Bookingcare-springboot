package com.bookingcare.service.Impl;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.exception.BaseException;
import com.bookingcare.model.dto.*;
import com.bookingcare.model.entity.DoctorInfor;
import com.bookingcare.model.entity.Markdown;
import com.bookingcare.model.entity.Schedule;
import com.bookingcare.repository.DoctorInforRepository;
import com.bookingcare.repository.MarkdownRepository;
import com.bookingcare.repository.ScheduleRepository;
import com.bookingcare.repository.SpecialtyRepository;
import com.bookingcare.security.entities.User;
import com.bookingcare.security.repo.IUserRepository;
import com.bookingcare.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Value("${MAX_NUMBER_SCHEDULE}")
    private int maxNumberSchedule;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    SpecialtyRepository specialtyRepository;

    @Autowired
    MarkdownRepository markdownRepository;
    @Autowired
    DoctorInforRepository doctorInforRepository;
    @Autowired
    ScheduleRepository scheduleRepository;



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

            return new ApiResponse(0, "Success", users);
        } catch (Exception e) {
            // Handle exceptions
            return new ApiResponse(-1, "Error from server...", null);
        }
    }

    @Override
    public ApiResponse<List<User>> getAllDoctors() {
        try {
            List<User> doctors = userRepository.findAllByRoleId("R2");

            // Loại bỏ các trường không cần thiết từ đối tượng User
            doctors.forEach(user -> {
                user.setPassword(null);
                user.setImage(null);
            });

            return new ApiResponse<>(0, "Success", doctors);

        } catch (Exception e) {
            // Xử lý lỗi và ném ra BaseException
            throw new BaseException(500,"An error occurred while fetching doctors.");
        }
    }

    @Override
    public ApiResponse saveDetailInforDoctor(DoctorDTO doctorDTO) {
        try {

            if (Objects.isNull(doctorDTO.getDoctorId()) || Objects.isNull(doctorDTO.getContentHTML()) || Objects.isNull(doctorDTO.getContentMarkdown())) {
                return new ApiResponse(1, "Missing parameter", null);
            }


            if ("CREATE".equals(doctorDTO.getAction())) {
                Markdown markdown = new Markdown();
                setMarkdownFields(markdown, doctorDTO);
                Date currentDate = new Date();
                markdown.setCreatedAt(currentDate);
                markdown.setUpdatedAt(currentDate);
                markdownRepository.save(markdown);
            } else if ("EDIT".equals(doctorDTO.getAction())) {
                Optional<Markdown> optionalMarkdown = markdownRepository.findByDoctorId(doctorDTO.getDoctorId());
                optionalMarkdown.ifPresent(markdown -> {
                    setMarkdownFields(markdown, doctorDTO);
                    markdownRepository.save(markdown);
                });
            }

            Optional<DoctorInfor> optionalDoctorInfor = doctorInforRepository.findByDoctorId(doctorDTO.getDoctorId());
            if (optionalDoctorInfor.isPresent()) {
                DoctorInfor doctorInfor = optionalDoctorInfor.get();
                setDoctorInforFields(doctorInfor, doctorDTO);
                doctorInforRepository.save(doctorInfor);
            } else {
                DoctorInfor doctorInfor = new DoctorInfor();
                setDoctorInforFields(doctorInfor, doctorDTO);
                Date curentDate = new Date();
                doctorInfor.setCreatedAt(curentDate);
                doctorInfor.setUpdatedAt(curentDate);
                doctorInforRepository.save(doctorInfor);

            }

            return new ApiResponse(0, "Save doctor information successfully!",null );
        } catch (Exception e) {
            throw new BaseException(500,"Undefined error: " + e.getMessage());
        }
    }

    private void setMarkdownFields(Markdown markdown, DoctorDTO doctorDTO) {
        markdown.setContentHTML(doctorDTO.getContentHTML());
        markdown.setContentMarkdown(doctorDTO.getContentMarkdown());
        markdown.setDescription(doctorDTO.getDescription());
        markdown.setDoctorId(doctorDTO.getDoctorId());
    }

    private void setDoctorInforFields(DoctorInfor doctorInfor, DoctorDTO doctorDTO) {
        doctorInfor.setDoctorId(doctorDTO.getDoctorId());
        doctorInfor.setPriceId(doctorDTO.getSelectedPrice());
        doctorInfor.setProvinceId(doctorDTO.getSelectedProvince());
        doctorInfor.setPaymentId(doctorDTO.getSelectedPayment());
        doctorInfor.setNameClinic(doctorDTO.getNameClinic());
        doctorInfor.setAddressClinic(doctorDTO.getAddressClinic());
        doctorInfor.setNote(doctorDTO.getNote());
        doctorInfor.setSpecialtyId(doctorDTO.getSpecialtyId());
        doctorInfor.setClinicId(doctorDTO.getClinicId());
    }

    @Override
    public ApiResponse<User> getDetailDoctorById(Integer inputId) {
        try {
            if (inputId == null) {
                return new ApiResponse<>(1, "Missing required parameter!", null);
            } else {
                User user = userRepository.findById(inputId).orElse(null);

                if (user != null) {
                    return new ApiResponse<>(0, "Success", user);
                } else {
                    return new ApiResponse<>(1, "User not found", null);
                }
            }
        } catch (Exception e) {
            throw new BaseException(500, "Internal Server Error");
        }
    }
    @Override
    public ApiResponse<Object> bulkCreateSchedule(ScheduleItem request) {
        try {
            if (request.getArrSchedule() == null || request.getDoctorId() == null || request.getFormatedDate() == null) {
                return new ApiResponse<>(1, "Missing required parameter!", null);
            }

            List<ScheduleDTO> schedule = request.getArrSchedule();
            if (schedule != null && !schedule.isEmpty()) {
                schedule.forEach(item -> item.setMaxNumber(maxNumberSchedule));
            }

            // Get all existing data
            List<Schedule> existing = scheduleRepository.findByDoctorIdAndDate(request.getDoctorId(), request.getFormatedDate());

            // Compare difference
            List<ScheduleDTO> toCreate = schedule.stream()
                    .filter(item -> existing.stream().noneMatch(existingItem ->
                            item.getTimeType().equals(existingItem.getTimeType()) && item.getDate().equals(existingItem.getDate())
                    ))
                    .collect(Collectors.toList());

            // Convert ScheduleDTO to Schedule
            List<Schedule> toCreateEntities = toCreate.stream()
                    .map(item -> {
                        Schedule scheduleEntity = new Schedule();
                        // Set properties from ScheduleDTO to Schedule
                        scheduleEntity.setTimeType(item.getTimeType());
                        scheduleEntity.setDate(item.getDate());
                        scheduleEntity.setDoctorId(item.getDoctorId());
                        scheduleEntity.setMaxNumber(item.getMaxNumber());
                        Date currentDate = new Date();
                        scheduleEntity.setCreatedAt(currentDate);
                        scheduleEntity.setUpdatedAt(currentDate);
                        return scheduleEntity;
                    })
                    .collect(Collectors.toList());

            // Create data
            if (!toCreateEntities.isEmpty()) {
                List<Schedule> createdSchedules = scheduleRepository.saveAll(toCreateEntities);
            }

            return new ApiResponse<>(0, "OK", null);
        } catch (Exception e) {
            throw new BaseException(-1, "Error from the server");
        }
    }

    @Override
    public ApiResponse<List<Schedule>> getScheduleByDate(Integer doctorId, String date) {
        try {
            if (doctorId == null || date == null) {
                throw new BaseException(1, "Missing required parameters!");
            }

            List<Schedule> dataSchedule = scheduleRepository.findByDoctorIdAndDate(doctorId, date);
            return new ApiResponse<>(0, "OK", dataSchedule);
        } catch (Exception e) {
            throw new BaseException(-1, "Error fetching schedule");
        }
    }

    @Override
    public ApiResponse<DoctorInfor> getExtraInforDoctorById(Integer idInput) {
        try {
            if (idInput == null) {
                throw new BaseException(1, "Missing required parameter!");
            }

            Optional<DoctorInfor> doctorInforOptional = doctorInforRepository.findByDoctorId(idInput);
            if (doctorInforOptional.isPresent()) {
                DoctorInfor doctorInfor = doctorInforOptional.get();
                return new ApiResponse<>(0, "Success", doctorInfor);
            } else {
                return new ApiResponse<>(1, "Doctor not found", null);
            }
        } catch (Exception e) {
            throw new BaseException(-1, "Error from the server");
        }
    }

    @Override
    public ApiResponse<Object> getProfileDoctorById(Integer inputId) {
        try {
            if (inputId == null) {
                return new ApiResponse<>(1, "Missing required parameter!", null);
            }

            User user = userRepository.findById(inputId).orElse(null);
            if (user == null) {
                return new ApiResponse<>(1, "User not found!", null);
            } else {
               UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(user, userDTO, "password");
                if (user.getMarkdown() != null) {
                    MarkdownDTO markdownDTO = new MarkdownDTO();
                    markdownDTO.setDescription(user.getMarkdown().getDescription());
                    markdownDTO.setDescription(user.getMarkdown().getContentHTML());
                    markdownDTO.setDescription(user.getMarkdown().getContentMarkdown());
                    userDTO.setMarkdown(markdownDTO);
                }

                // Copy các trường từ DoctorInfor
                if (user.getDoctorInfor() != null) {
                    DoctorInforDTO doctorInforDTO = new DoctorInforDTO();
                    doctorInforDTO.setPriceTypeData(user.getDoctorInfor().getPriceTypeData());
                    doctorInforDTO.setProvinceTypeData(user.getDoctorInfor().getProvinceTypeData());
                    doctorInforDTO.setPaymentTypeData(user.getDoctorInfor().getPaymentTypeData());
                    userDTO.setDoctorInfor(doctorInforDTO);
                }
                return new ApiResponse<>(0, "OK", userDTO);
            }

        } catch (Exception e) {
            throw new BaseException(-1, "Error from the server");
        }
    }



}





