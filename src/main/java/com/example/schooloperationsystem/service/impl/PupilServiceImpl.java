package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.entity.School;
import com.example.schooloperationsystem.repository.PupilRepository;
import com.example.schooloperationsystem.repository.SchoolRepository;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.SchoolService;
import com.example.schooloperationsystem.service.params.CreatePupilParams;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

        School school = schoolService.getById(params.getSchoolId());

        pupil.setFirstName(params.getFirstName());
        pupil.setLastName(params.getLastName());
        pupil.setDateOfBirth(params.getDateOfBirth());
        pupil.setSchool(school);

        log.debug("Successfully executed add pupil, {}", pupil);
        return repository.save(pupil);
    }

    @Override
    @Transactional(readOnly = true)
    public Pupil getById(Long id) {
        log.debug("Executing get pupil by id, id-{}", id);

        Pupil pupil = repository.getByIdAndDeletedAtIsNull(id);

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

        Optional<Pupil> pupilOptional = repository.findById(id);

        if (pupilOptional.isPresent()) {
            Pupil pupil = pupilOptional.get();

            pupil.setDeletedAt(LocalDateTime.now());

            log.debug("Successfully executed delete pupil, {}", pupil);
            return repository.save(pupil);
        }

        log.debug("No pupil with id-{}", id);
        return null;
    }

    @Override
    public List<Pupil> getBySchoolId(Long schoolId) {
        log.debug("Executing get pupils by school id, id-{}", schoolId);

        List<Pupil> pupils = repository.findAllBySchool_IdAndDeletedAtIsNull(schoolId);

        log.debug("Successfully executed get all pupils by school id, {}", pupils);
        return pupils;
    }

}