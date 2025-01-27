package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.service.params.CreatePupilInClassParams;

import java.util.List;

public interface PupilInClassService {
    List<PupilInClass> get();

    PupilInClass add(CreatePupilInClassParams params);

    PupilInClass deleteById(Long id);

    List<PupilInClass> getBySchoolClassId(Long schoolClassId);
}