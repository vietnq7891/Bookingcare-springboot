package com.bookingcare.security.service;


import com.bookingcare.security.entities.Role;
import com.bookingcare.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}