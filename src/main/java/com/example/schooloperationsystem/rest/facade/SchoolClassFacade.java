package com.example.schooloperationsystem.rest.facade;

import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;

import java.util.List;

public interface SchoolClassFacade {
    List<SchoolClassDetailsDto> getClasses();

    SchoolClassDetailsDto createClass(CreateSchoolClassRequestDto requestDto);

    SchoolClassDetailsDto getClassById(Long id);

    List<SchoolClassDetailsDto> getClassesBySchoolId(Long schoolId);
}
