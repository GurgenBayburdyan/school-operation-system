package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.School;
import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.repository.StaffRepository;
import com.example.schooloperationsystem.service.HeadMasterService;
import com.example.schooloperationsystem.service.SchoolService;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
class StaffServiceImpl implements StaffService {

    private final StaffRepository repository;
    private final SchoolService schoolService;

    @Override
    @Transactional(readOnly = true)
    public List<Staff> get() {
        log.debug("Executing get all staff");

        List<Staff> staffList = repository.findAllByDeletedAtIsNull();

        log.debug("Successfully executed get all staff, {}", staffList);
        return staffList;
    }

    @Override
    @Transactional
    public Staff add(CreateStaffParams params) {
        log.debug("Executing add staff, params-{}", params);

        Staff staff = new Staff();

        School school = schoolService.getById(params.getSchoolId());

        staff.setFirstName(params.getFirstName());
        staff.setLastName(params.getLastName());
        staff.setDateOfBirth(params.getDateOfBirth());
        staff.setSchool(school);

        log.debug("Successfully executed add staff, {}", staff);
        return repository.save(staff);
    }

    @Override
    @Transactional(readOnly = true)
    public Staff getById(Long id) {
        log.debug("Executing get staff by id, id-{}", id);

        Staff staff = repository.findById(id).orElse(null);

        if (staff != null) {
            log.debug("Successfully executed get staff by id, {}", staff);
        } else {
            log.debug("No staff found with id, {}", id);
        }

        return staff;
    }

    @Override
    @Transactional
    public Staff deleteById(Long id) {
        log.debug("Executing delete staff by id, id-{}", id);

        Optional<Staff> staffOptional = repository.findById(id);

        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();

            staff.setDeletedAt(LocalDateTime.now());

            log.debug("Successfully executed delete staff, {}", staff);
            return repository.save(staff);
        }

        log.debug("No staff with id-{}", id);
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}