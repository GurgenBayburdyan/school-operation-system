package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.repository.PupilRepository;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.params.CreatePupilParams;
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
class PupilServiceImpl implements PupilService {
    private final PupilRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(PupilServiceImpl.class);

    @Override
    @Transactional
    public List<Pupil> getPupils() {
        logger.info("Executing get all pupils");

        List<Pupil> pupils = repository.findAll();

        logger.info("Successfully executed get all pupils, {}", pupils);
        return pupils;
    }

    @Override
    @Transactional
    public Pupil addPupil(CreatePupilParams params) {
        logger.info("Executing add pupil, params-{}", params);

        Pupil pupil = new Pupil();
        pupil.setFirstName(params.getFirstName());
        pupil.setLastName(params.getLastName());
        pupil.setDateOfBirth(params.getDateOfBirth());

        logger.info("Successfully executed add pupil, {}", pupil);
        return repository.save(pupil);
    }

    @Override
    @Transactional
    public Pupil getPupilById(Long id) {
        logger.info("Executing get pupil by id, id-{}", id);

        Pupil pupil = repository.getReferenceById(id);

        logger.info("Successfully executed get pupil by id, {}", pupil);
        return pupil;
    }
}
