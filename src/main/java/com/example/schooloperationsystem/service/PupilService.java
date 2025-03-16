package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.service.params.CreatePupilParams;
import java.util.List;

public interface PupilService {
    List<Pupil> get();

    Pupil add(CreatePupilParams params);

    Pupil findById(Long id);

    Boolean existsById(Long id);

    Pupil deleteById(Long id);

    List<Pupil> findBySchoolId(Long schoolId);
}
