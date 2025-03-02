package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.entity.School;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.repository.SchoolClassRepository;
import com.example.schooloperationsystem.repository.SchoolRepository;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.SchoolService;
import com.example.schooloperationsystem.service.params.CreateSchoolParams;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<School> get() {
        log.debug("Executing get all schools");

        List<School> schoolList = repository.findAllByDeletedAtIsNull();

        log.debug("Successfully executed get all schools, {}", schoolList);
        return schoolList;
    }

    @Override
    @Transactional
    public School create(CreateSchoolParams params) {
        log.debug("Executing create school, params-{}", params);

        School school = new School();

        school.setNumber(params.getNumber());
        school.setNamedAfter(params.getNamedAfter());
        school.setAddress(params.getAddress());
        school.setPhotoUrl(params.getPhotoUrl());

        log.debug("Successfully executed create school, {}", school);
        return repository.save(school);
    }

    @Override
    @Transactional(readOnly = true)
    public School getById(Long id) {
        log.debug("Executing get school by id, id-{}", id);

        School school = repository.findByIdAndDeletedAtIsNull(id).orElse(null);

        if (school != null) {
            log.debug("Successfully executed get school by id, {}", school);
        } else {
            log.debug("No school found with id-{}", id);
        }

        return school;
    }

    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public School deleteById(Long id) {
        log.debug("Executing delete school by id, id-{}", id);

        Optional<School> schoolOptional = repository.findByIdAndDeletedAtIsNull(id);

        if (schoolOptional.isPresent()) {
            School school = schoolOptional.get();

            school.setDeletedAt(LocalDateTime.now());

            log.debug("Successfully executed delete pupil in class, {}", school);
            return school;
        }

        log.debug("No pupil in class with pupil id-{}", id);
        return null;
    }
}
