package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
import java.util.List;

public interface StaffService {
    List<Staff> get();

    Staff add(CreateStaffParams params);

    Staff findById(Long id);

    Boolean existsById(Long id);

    Staff deleteById(Long id);

    List<Staff> findBySchoolId(Long schoolId);
}
