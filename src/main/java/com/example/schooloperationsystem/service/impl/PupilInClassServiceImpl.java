package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.repository.PupilInClassRepository;
import com.example.schooloperationsystem.service.PupilInClassService;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.params.CreatePupilInClassParams;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PupilInClassServiceImpl.class);

    @Override
    public List<PupilInClass> getPupilInClasses() {
        logger.info("Executing get all pupil in classes");

        List<PupilInClass> pupilInClasses = repository.findAll();

        logger.info("Successfully executed get pupil in classes, {}", pupilInClasses);
        return pupilInClasses;
    }

    @Override
    public PupilInClass addPupil(CreatePupilInClassParams params) {
        logger.info("Executing add pupil in class, params-{}", params);

        Pupil pupil = pupilService.getPupilById(params.getPupilId());
        SchoolClass schoolClass = schoolClassService.getClassById(params.getSchoolClassId());

        PupilInClass pupilInClass = new PupilInClass();

        pupilInClass.setPupil(pupil);
        pupilInClass.setSchoolClass(schoolClass);

        logger.info("Successfully executed add pupil in class, {}", pupilInClass);
        return repository.save(pupilInClass);
    }

    @Override
    @Transactional
    public PupilInClass deletePupilById(Long id) {
        log.info("Executing delete pupil in class by id, id-{}", id);

        Optional<PupilInClass> pupilInClassOptional = repository.findById(id);
        if (pupilInClassOptional.isPresent()) {
            PupilInClass pupilInClass = pupilInClassOptional.get();
            repository.delete(pupilInClass);

            log.info("Successfully executed delete pupil in class, {}", pupilInClass);
            return pupilInClass;
        }

        log.info("No pupil in class with id-{}", id);
        return null;
    }

    @Override
    @Transactional
    public List<PupilInClass> getPupilsBySchoolClassId(Long schoolClassId) {
        log.info("Executing get pupils in class by class id, id-{}", schoolClassId);

        List<PupilInClass> pupilInClasses = repository.getBySchoolClassId(schoolClassId);

        log.info("Successfully executed get pupils in class by class id, {}", pupilInClasses);
        return pupilInClasses;
    }
}
