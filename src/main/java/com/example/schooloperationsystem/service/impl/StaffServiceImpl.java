package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.School;
import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.repository.StaffRepository;
import com.example.schooloperationsystem.service.SchoolService;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

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

        School school = schoolService.findById(params.getSchoolId());

        staff.setFirstName(params.getFirstName());
        staff.setLastName(params.getLastName());
        staff.setDateOfBirth(params.getDateOfBirth());
        staff.setSchool(school);

        log.debug("Successfully executed add staff, {}", staff);
        return repository.save(staff);
    }

    @Override
    @Transactional(readOnly = true)
    public Staff findById(Long id) {
        log.debug("Executing get staff by id, id-{}", id);

        Staff staff = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Staff not found")
        );

        log.debug("Successfully executed get staff by id, {}", staff);
        return staff;
    }

    @Override
    @Transactional
    public Staff deleteById(Long id) {
        log.debug("Executing delete staff by id, id-{}", id);

        Staff staff = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Staff not found")
        );

        staff.setDeletedAt(LocalDateTime.now());

        log.debug("Successfully executed delete staff, {}", staff);
        return repository.save(staff);
    }

    @Override
    public List<Staff> findBySchoolId(Long schoolId) {
        log.debug("Executing get all staff by school id, id-{}", schoolId);

        List<Staff> staffList = repository.findAllBySchool_IdAndDeletedAtIsNull(schoolId);

        log.debug("Successfully executed get all staff by school id, {}", staffList);
        return staffList;
    }

    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}