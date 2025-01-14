package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.repository.PupilInClassRepository;
import com.example.schooloperationsystem.service.PupilInClassService;
import com.example.schooloperationsystem.service.params.CreatePupilInClassParams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PupilInClassServiceImpl implements PupilInClassService {
    private final PupilInClassRepository repository;

    public PupilInClassServiceImpl(PupilInClassRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PupilInClass> getPupilInClasses() {
        return repository.findAll();
    }

    @Override
    public PupilInClass addPupil(CreatePupilInClassParams params) {
        PupilInClass pupilInClass = new PupilInClass();
        pupilInClass.setPupil(params.getPupil());
        pupilInClass.setSchoolClass(params.getSchoolClass());
        return repository.save(pupilInClass);
    }
}
