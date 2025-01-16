package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.repository.SchoolClassRepository;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.params.CreateSchoolClassParams;
import com.example.schooloperationsystem.entity.SchoolClass;
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
class SchoolClassServiceImpl implements SchoolClassService {

    private SchoolClassRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(SchoolClassServiceImpl.class);

    @Override
    @Transactional
    public List<SchoolClass> getClasses() {
        logger.info("Executing get all school classes");

        List<SchoolClass> classes = repository.findAll();

        logger.info("Successfully executed get all school classes, {}", classes);
        return classes;
    }

    @Override
    @Transactional
    public SchoolClass addClass(CreateSchoolClassParams params) {
        logger.info("Executing add school class, params-{}", params);

        SchoolClass classEntity = new SchoolClass();
        classEntity.setGrade(params.getGrade());
        classEntity.setLetter(params.getClassLetter());

        logger.info("Successfully executed add school class, {}", classEntity);
        return repository.save(classEntity);
    }

    @Override
    @Transactional
    public SchoolClass getClassById(Long id) {
        logger.info("Executing get school class by id, id-{}", id);

        SchoolClass schoolClass =  repository.findById(id).orElse(null);

        if (schoolClass != null) {
            logger.info("Successfully executed get school class by id, {}", schoolClass);
            return schoolClass;
        } else {
            logger.info("No school class found with id-{}", id);
        }

        return null;
    }
}
