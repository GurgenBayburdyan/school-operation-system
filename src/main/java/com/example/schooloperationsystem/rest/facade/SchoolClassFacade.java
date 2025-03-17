package com.example.schooloperationsystem.rest.facade;

import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;
import java.util.List;

public interface SchoolClassFacade {
    List<SchoolClassDetailsDto> getAll();

    SchoolClassDetailsDto create(CreateSchoolClassRequestDto requestDto);

    SchoolClassDetailsDto getById(Long id);

    List<SchoolClassDetailsDto> getBySchoolId(Long schoolId);
}
