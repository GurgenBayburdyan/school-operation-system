package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.School;
import com.example.schooloperationsystem.repository.SchoolClassRepository;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.SchoolService;
import com.example.schooloperationsystem.service.params.CreateSchoolClassParams;
import com.example.schooloperationsystem.entity.SchoolClass;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
class SchoolClassServiceImpl implements SchoolClassService {

    private final SchoolClassRepository repository;
    private final SchoolService schoolService;

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
        School school = schoolService.findById(params.getSchoolId());

        schoolClass.setGrade(params.getGrade());
        schoolClass.setLetter(params.getClassLetter());
        schoolClass.setSchool(school);

        log.debug("Successfully executed add school class, {}", schoolClass);
        return repository.save(schoolClass);
    }

    @Override
    @Transactional(readOnly = true)
    public SchoolClass findById(Long id) {
        log.debug("Executing get school class by id, id-{}", id);

        SchoolClass schoolClass = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("School class not found")
        );

        log.debug("Successfully executed get class by id, {}", schoolClass);
        return schoolClass;

    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SchoolClass> findBySchoolId(Long schoolId) {
        log.debug("Executing get all school classes by school id, id-{}", schoolId);

        List<SchoolClass> classes = repository.findBySchool_IdAndSchool_DeletedAtIsNull(schoolId);

        log.debug("Successfully executed get all school classes by school id, {}", classes);
        return classes;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean exists(Long schoolId, Integer grade, Character letter) {
        SchoolClass schoolClass = repository.findBySchoolIdAndGradeAndLetter(schoolId, grade, letter);
        return schoolClass != null;
    }

}
