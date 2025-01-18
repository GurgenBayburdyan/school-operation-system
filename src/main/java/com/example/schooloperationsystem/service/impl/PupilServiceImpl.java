package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.repository.PupilRepository;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.params.CreatePupilParams;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
class PupilServiceImpl implements PupilService {

    private final PupilRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Pupil> getPupils() {
        log.debug("Executing get all pupils");

        List<Pupil> pupils = repository.findAll();

        log.debug("Successfully executed get all pupils, {}", pupils);
        return pupils;
    }

    @Override
    @Transactional
    public Pupil addPupil(CreatePupilParams params) {
        log.debug("Executing add pupil, params-{}", params);

        Pupil pupil = new Pupil();
        pupil.setFirstName(params.getFirstName());
        pupil.setLastName(params.getLastName());
        pupil.setDateOfBirth(params.getDateOfBirth());

        log.debug("Successfully executed add pupil, {}", pupil);
        return repository.save(pupil);
    }

    @Override
    @Transactional(readOnly = true)
    public Pupil getPupilById(Long id) {
        log.debug("Executing get pupil by id, id-{}", id);

        Pupil pupil = repository.getReferenceById(id);

        log.debug("Successfully executed get pupil by id, {}", pupil);
        return pupil;
    }
}
