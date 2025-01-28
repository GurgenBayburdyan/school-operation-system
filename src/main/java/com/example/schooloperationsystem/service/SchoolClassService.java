package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.service.params.CreateSchoolClassParams;
import com.example.schooloperationsystem.entity.SchoolClass;

import java.util.List;


public interface SchoolClassService {
    List<SchoolClass> get();

    SchoolClass add(CreateSchoolClassParams params);

    SchoolClass getById(Long id);

    Boolean existsById(Long id);
}
