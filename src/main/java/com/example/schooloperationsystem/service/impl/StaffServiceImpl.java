package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.repository.StaffRepository;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
class StaffServiceImpl implements StaffService {

    private final StaffRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

    @Override
    @Transactional
    public List<Staff> getStaff() {
        logger.info("Executing get all staff");

        List<Staff> staffList = repository.findAll();

        logger.info("Successfully executed get all staff, {}", staffList);
        return staffList;
    }

    @Override
    @Transactional
    public Staff addStaff(CreateStaffParams params) {
        logger.info("Executing add staff, params-{}", params);

        Staff staff = new Staff();
        staff.setFirstName(params.getFirstName());
        staff.setLastName(params.getLastName());
        staff.setDateOfBirth(params.getDateOfBirth());

        logger.info("Successfully executed add staff, {}", staff);
        return repository.save(staff);
    }

    @Override
    @Transactional
    public Staff getById(Long id) {
        logger.info("Executing get staff by id, id-{}", id);

        Staff staff = repository.findById(id).orElse(null);

        if (staff != null) {
            logger.info("Successfully executed get staff by id, {}", staff);
        } else {
            logger.info("No staff found with id-{}", id);
        }

        return staff;
    }
}
