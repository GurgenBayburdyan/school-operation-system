package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.repository.PupilRepository;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.params.CreatePupilParams;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class PupilServiceImpl implements PupilService {
    private final PupilRepository repository;

    @Override
    @Transactional
    public List<Pupil> getPupils() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Pupil addPupil(CreatePupilParams params) {
        Pupil pupil = new Pupil();
        pupil.setFirstName(params.getFirstName());
        pupil.setLastName(params.getLastName());
        pupil.setDateOfBirth(params.getDateOfBirth());
        return repository.save(pupil);
    }

    @Override
    @Transactional
    public Pupil getPupilById(Long id) {
        return repository.getReferenceById(id);
    }
}
