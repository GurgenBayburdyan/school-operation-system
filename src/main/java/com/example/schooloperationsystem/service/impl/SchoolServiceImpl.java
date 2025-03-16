package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.School;
import com.example.schooloperationsystem.repository.SchoolRepository;
import com.example.schooloperationsystem.service.SchoolService;
import com.example.schooloperationsystem.service.params.CreateSchoolParams;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
    public School findById(Long id) {
        log.debug("Executing get school by id, id-{}", id);

        School school = repository.findByIdAndDeletedAtIsNull(id).orElseThrow(
                () -> new EntityNotFoundException("School not found")
        );

        log.debug("Successfully executed get school by id, {}", school);
        return school;
    }

    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
