package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.repository.PupilInClassRepository;
import com.example.schooloperationsystem.service.PupilInClassService;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.params.CreatePupilInClassParams;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
class PupilInClassServiceImpl implements PupilInClassService {

    private final PupilInClassRepository repository;
    private final PupilService pupilService;
    private final SchoolClassService schoolClassService;

    @Override
    @Transactional(readOnly = true)
    public List<PupilInClass> get() {
        log.debug("Executing get all pupil in classes");

        List<PupilInClass> pupilInClasses = repository.findAll();

        log.debug("Successfully executed get pupil in classes, {}", pupilInClasses);
        return pupilInClasses;
    }

    @Override
    @Transactional
    public PupilInClass add(CreatePupilInClassParams params) {
        log.debug("Executing add pupil in class, params-{}", params);

        Pupil pupil = pupilService.getById(params.getPupilId());
        SchoolClass schoolClass = schoolClassService.getById(params.getSchoolClassId());

        PupilInClass pupilInClass = new PupilInClass();

        pupilInClass.setPupil(pupil);
        pupilInClass.setSchoolClass(schoolClass);

        log.debug("Successfully executed add pupil in class, {}", pupilInClass);
        return repository.save(pupilInClass);
    }

    @Override
    @Transactional
    public PupilInClass deleteByPupilId(Long id) {
        log.debug("Executing delete pupil in class by pupil id, id-{}", id);

        PupilInClass pupilInClass = repository.findByPupil_Id(id);

        if (pupilInClass != null) {
            repository.delete(pupilInClass);

            log.debug("Successfully executed delete pupil in class, {}", pupilInClass);
            return pupilInClass;
        }

        log.debug("No pupil in class with pupil id-{}", id);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PupilInClass> getBySchoolClassId(Long schoolClassId) {
        log.debug("Executing get pupils in class by class id, id-{}", schoolClassId);

        List<PupilInClass> pupilInClasses = repository.getBySchoolClassId(schoolClassId);

        log.debug("Successfully executed get pupils in class by class id, {}", pupilInClasses);
        return pupilInClasses;
    }

    @Override
    public PupilInClass getByPupilId(Long pupilId) {
        log.debug("Executing get pupil in class by pupil id, id-{}", pupilId);

        PupilInClass pupilInClass = repository.findByPupil_Id(pupilId);

        log.debug("Successfully executed get pupil in class by pupil id, {}", pupilInClass);
        return pupilInClass;
    }
}
