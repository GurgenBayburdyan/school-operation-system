package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.service.params.CreatePupilParams;

import java.util.List;

public interface PupilService {
    List<Pupil> getPupils();

    Pupil addPupil(CreatePupilParams params);

    Pupil getPupilById(Long id);
}
