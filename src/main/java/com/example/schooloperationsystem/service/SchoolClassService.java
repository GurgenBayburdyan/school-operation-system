package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.service.params.CreateSchoolClassParams;
import com.example.schooloperationsystem.entity.SchoolClass;
import java.util.List;

public interface SchoolClassService {
    List<SchoolClass> get();

    SchoolClass add(CreateSchoolClassParams params);

    SchoolClass findById(Long id);

    Boolean existsById(Long id);

    List<SchoolClass> findBySchoolId(Long schoolId);

    Boolean exists(Long schoolId, Integer grade, Character classLetter);

}
