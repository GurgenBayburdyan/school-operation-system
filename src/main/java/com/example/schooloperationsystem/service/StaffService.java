package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.service.params.CreateStaffParams;

import java.util.List;

public interface StaffService {
    List<Staff> get();

    Staff add(CreateStaffParams params);

    Staff getById(Long id);

    boolean existsById(Long id);
}
