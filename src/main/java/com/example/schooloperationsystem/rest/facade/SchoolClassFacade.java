package com.example.schooloperationsystem.rest.facade;

import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SchoolClassFacade {
    List<SchoolClassDetailsDto> getClasses();

    SchoolClassDetailsDto createClass(CreateSchoolClassRequestDto requestDto);

    SchoolClassDetailsDto getClassById(Long id);

    List<SchoolClassDetailsDto> getClassesBySchoolId(Long schoolId);
}
