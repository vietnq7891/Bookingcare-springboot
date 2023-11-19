package com.bookingcare.service;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.security.entities.User;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {
    List<T> findAll();

    Optional<T> findById(Integer id);

    ApiResponse<T>  save(T t);

    ApiResponse remove(Integer id);
}
