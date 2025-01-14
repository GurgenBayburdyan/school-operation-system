package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.repository.PupilRepository;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.params.CreatePupilParams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PupilServiceImpl implements PupilService {
    private final PupilRepository repository;

    public PupilServiceImpl(PupilRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Pupil> getPupils() {
        return repository.findAll();
    }

    @Override
    public Pupil addPupil(CreatePupilParams params) {
        Pupil pupil = new Pupil();
        pupil.setFirstName(params.getFirstName());
        pupil.setLastName(params.getLastName());
        pupil.setDateOfBirth(params.getDateOfBirth());
        return repository.save(pupil);
    }

    @Override
    public Pupil getPupilById(Long id) {
        return repository.getReferenceById(id);
    }
}
