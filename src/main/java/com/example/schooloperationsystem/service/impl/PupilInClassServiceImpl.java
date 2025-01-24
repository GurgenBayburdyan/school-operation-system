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
    public List<PupilInClass> getPupilInClasses() {
        log.debug("Executing get all pupil in classes");

        List<PupilInClass> pupilInClasses = repository.findAll();

        log.debug("Successfully executed get pupil in classes, {}", pupilInClasses);
        return pupilInClasses;
    }

    @Override
    @Transactional
    public PupilInClass addPupil(CreatePupilInClassParams params) {
        log.debug("Executing add pupil in class, params-{}", params);

        Pupil pupil = pupilService.getPupilById(params.getPupilId());
        SchoolClass schoolClass = schoolClassService.getClassById(params.getSchoolClassId());

        PupilInClass pupilInClass = new PupilInClass();

        pupilInClass.setPupil(pupil);
        pupilInClass.setSchoolClass(schoolClass);

        log.debug("Successfully executed add pupil in class, {}", pupilInClass);
        return repository.save(pupilInClass);
    }

    @Override
    @Transactional
    //todo please validate pupil existence in controller, if not found return PUPIL_NOT_FOUND error. 
    public PupilInClass deletePupilById(Long id) {
        log.debug("Executing delete pupil in class by id, id-{}", id);

        Optional<PupilInClass> pupilInClassOptional = repository.findById(id);
        if (pupilInClassOptional.isPresent()) {
            PupilInClass pupilInClass = pupilInClassOptional.get();
            repository.delete(pupilInClass);

            log.debug("Successfully executed delete pupil in class, {}", pupilInClass);
            return pupilInClass;
        }

        log.debug("No pupil in class with id-{}", id);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PupilInClass> getPupilsBySchoolClassId(Long schoolClassId) {
        log.debug("Executing get pupils in class by class id, id-{}", schoolClassId);

        List<PupilInClass> pupilInClasses = repository.getBySchoolClassId(schoolClassId);

        log.debug("Successfully executed get pupils in class by class id, {}", pupilInClasses);
        return pupilInClasses;
    }
}
