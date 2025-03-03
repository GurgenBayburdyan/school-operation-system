package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.School;
import com.example.schooloperationsystem.repository.SchoolClassRepository;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.SchoolService;
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
    private SchoolService schoolService;

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

        SchoolClass schoolClass = new SchoolClass();
        School school = schoolService.getById(params.getSchoolId());

        schoolClass.setGrade(params.getGrade());
        schoolClass.setLetter(params.getClassLetter());
        schoolClass.setSchool(school);

        log.debug("Successfully executed add school class, {}", schoolClass);
        return repository.save(schoolClass);
    }

    @Override
    @Transactional(readOnly = true)
    public SchoolClass getById(Long id) {
        log.debug("Executing get school class by id, id-{}", id);

        SchoolClass schoolClass = repository.findById(id).orElse(null);

        if (schoolClass != null) {
            log.debug("Successfully executed get class by id, {}", schoolClass);
        } else {
            log.debug("No class found with id-{}", id);
        }

        return schoolClass;

    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SchoolClass> getBySchoolId(Long schoolId) {
        return repository.findBySchool_IdAndSchool_DeletedAtIsNull(schoolId);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean exists(Long schoolId, Integer grade, Character letter) {
        SchoolClass schoolClass = repository.findBySchoolIdAndGradeAndLetter(schoolId, grade, letter);

        return schoolClass != null;
    }

}
