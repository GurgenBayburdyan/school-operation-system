package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.repository.StaffRepository;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository repository;

    public StaffServiceImpl(StaffRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Staff> getStaff() {
        return repository.findAll();
    }

    @Override
    public Staff addStaff(CreateStaffParams params) {
        Staff staff = new Staff();
        staff.setFirstName(params.getFirstName());
        staff.setLastName(params.getLastName());
        staff.setDateOfBirth(params.getDateOfBirth());
        return repository.save(staff);
    }
}
