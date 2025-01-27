package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.repository.SchoolClassRepository;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.params.CreateSchoolClassParams;
import com.example.schooloperationsystem.entity.SchoolClass;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
class SchoolClassServiceImpl implements SchoolClassService {

    private SchoolClassRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<SchoolClass> get() {
        log.debug("Executing get all school classes");

        List<SchoolClass> classes = repository.findAll();

        log.debug("Successfully executed get all school classes, {}", classes);
        return classes;
    }

    @Override
    @Transactional
    public SchoolClass add(CreateSchoolClassParams params) {
        log.debug("Executing add school class, params-{}", params);

        SchoolClass classEntity = new SchoolClass();
        
        classEntity.setGrade(params.getGrade());
        classEntity.setLetter(params.getClassLetter());

        log.debug("Successfully executed add school class, {}", classEntity);
        return repository.save(classEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public SchoolClass getById(Long id) {
        log.debug("Executing get school class by id, id-{}", id);

        SchoolClass schoolClass = repository.findById(id).orElseThrow();

        log.debug("Successfully executed get school class by id, {}", schoolClass);
        return schoolClass;

    }
}
