package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.entity.School;
import com.example.schooloperationsystem.repository.PupilRepository;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.SchoolService;
import com.example.schooloperationsystem.service.params.CreatePupilParams;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
class PupilServiceImpl implements PupilService {

    private final PupilRepository repository;
    private final SchoolService schoolService;

    @Override
    @Transactional(readOnly = true)
    public List<Pupil> get() {
        log.debug("Executing get all pupils");

        List<Pupil> pupils = repository.findAllByDeletedAtIsNull();

        log.debug("Successfully executed get all pupils, {}", pupils);
        return pupils;
    }

    @Override
    @Transactional
    public Pupil add(CreatePupilParams params) {
        log.debug("Executing add pupil, params-{}", params);

        Pupil pupil = new Pupil();

        School school = schoolService.findById(params.getSchoolId());

        pupil.setFirstName(params.getFirstName());
        pupil.setLastName(params.getLastName());
        pupil.setDateOfBirth(params.getDateOfBirth());
        pupil.setSchool(school);

        log.debug("Successfully executed add pupil, {}", pupil);
        return repository.save(pupil);
    }

    @Override
    @Transactional(readOnly = true)
    public Pupil findById(Long id) {
        log.debug("Executing get pupil by id, id-{}", id);

        Pupil pupil = repository.findByIdAndDeletedAtIsNull(id).orElseThrow(
                () -> new EntityNotFoundException("Pupil not found")
        );

        log.debug("Successfully executed get pupil by id, {}", pupil);
        return pupil;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsById(Long id) {
        return repository.existsByIdAndDeletedAtIsNull(id);
    }

    @Override
    public Pupil deleteById(Long id) {
        log.debug("Executing delete pupil by id, id-{}", id);

        Pupil pupil = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Pupil not found")
        );

        pupil.setDeletedAt(LocalDateTime.now());

        log.debug("Successfully executed delete pupil, {}", pupil);
        return repository.save(pupil);
    }

    @Override
    public List<Pupil> findBySchoolId(Long schoolId) {
        log.debug("Executing get pupils by school id, id-{}", schoolId);

        List<Pupil> pupils = repository.findAllBySchool_IdAndDeletedAtIsNull(schoolId);

        log.debug("Successfully executed get all pupils by school id, {}", pupils);
        return pupils;
    }

}