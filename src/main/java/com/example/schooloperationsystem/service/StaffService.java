package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.service.params.CreateStaffParams;

import java.util.List;

public interface StaffService {
    List<Staff> getStaff();
    Staff addStaff(CreateStaffParams params);
    Staff getStaffById(Long id);
}
