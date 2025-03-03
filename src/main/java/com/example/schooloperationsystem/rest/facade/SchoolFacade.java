package com.example.schooloperationsystem.rest.facade;

import com.example.schooloperationsystem.rest.dto.request.CreateSchoolRequestDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolDetailsDto;

import java.util.List;

public interface SchoolFacade {
    SchoolDetailsDto create(CreateSchoolRequestDto requestDto);

    List<SchoolDetailsDto> get();

    SchoolDetailsDto getById(Long id);
}
