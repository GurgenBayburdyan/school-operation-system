package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.entity.School;
import com.example.schooloperationsystem.service.params.CreateSchoolParams;
import java.util.List;

public interface SchoolService {

    List<School> get();

    School create(CreateSchoolParams createSchoolParams);

    Boolean existsById(Long id);

    School findById(Long id);
}
