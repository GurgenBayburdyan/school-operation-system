package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.repository.StaffRepository;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
class StaffServiceImpl implements StaffService {

    private final StaffRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Staff> get() {
        log.debug("Executing get all staff");

        List<Staff> staffList = repository.findAll();

        log.debug("Successfully executed get all staff, {}", staffList);
        return staffList;
    }

    @Override
    @Transactional
    public Staff add(CreateStaffParams params) {
        log.debug("Executing add staff, params-{}", params);

        Staff staff = new Staff();
        staff.setFirstName(params.getFirstName());
        staff.setLastName(params.getLastName());
        staff.setDateOfBirth(params.getDateOfBirth());

        log.debug("Successfully executed add staff, {}", staff);
        return repository.save(staff);
    }

    @Override
    @Transactional(readOnly = true)
    public Staff getById(Long id) {
        log.debug("Executing get staff by id, id-{}", id);

        Staff staff = repository.findById(id)
                .orElseThrow(() -> {
                    log.debug("No staff found with id-{}", id);
                    return new EntityNotFoundException();
                });

        log.debug("Successfully executed get staff by id, {}", staff);
        return staff;
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}