package com.example.schooloperationsystem.rest.facade.impl;


import com.example.schooloperationsystem.entity.School;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.mapper.SchoolMapper;
import com.example.schooloperationsystem.rest.controller.validator.SchoolValidator;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.SchoolDetailsDto;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;
import com.example.schooloperationsystem.rest.facade.SchoolFacade;
import com.example.schooloperationsystem.service.SchoolService;
import com.example.schooloperationsystem.service.params.CreateSchoolParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SchoolFacadeImpl implements SchoolFacade {

    private final SchoolService schoolService;
    private final SchoolMapper schoolMapper;
    private final SchoolValidator schoolValidator;

    @Override
    public SchoolDetailsDto create(CreateSchoolRequestDto requestDto) {
        log.info("Executing create school for the provided request to - {}", requestDto);

        Optional<ErrorType> optionalErrorType = schoolValidator.validateCreate(requestDto);

        if (optionalErrorType.isPresent()) {
            SchoolDetailsDto schoolClassDetailsDto = new SchoolDetailsDto();
            schoolClassDetailsDto.setErrorType(optionalErrorType.get());
            log.info("Executing create school failed, error-{}", optionalErrorType.get());
            return schoolClassDetailsDto;
        } else {
            CreateSchoolParams params = new CreateSchoolParams(
                    requestDto.getNumber(),
                    requestDto.getNamedAfter(),
                    requestDto.getAddress(),
                    requestDto.getPhotoUrl()
            );
            School response = schoolService.create(params);
            SchoolDetailsDto schoolDetailsDto = schoolMapper.map(response);

            log.info("Successfully executed create school rest API, response - {}", schoolDetailsDto);
            return schoolDetailsDto;
        }
    }

    @Override
    public List<SchoolDetailsDto> get() {
        log.info("Executing get all schools rest API");

        List<School> response = schoolService.get();
        List<SchoolDetailsDto> schoolDetailsDtos = schoolMapper.mapList(response);

        log.info("Successfully executed get schools rest API, response - {}", schoolDetailsDtos);
        return schoolDetailsDtos;
    }

    @Override
    public SchoolDetailsDto getById(Long id) {
        log.info("Executing get school by id-{}", id);

        School response = schoolService.getById(id);

        if (response == null) {
            SchoolDetailsDto schoolDetailsDto = new SchoolDetailsDto();
            schoolDetailsDto.setErrorType(ErrorType.SCHOOL_NOT_FOUND);
            return schoolDetailsDto;
        }

        SchoolDetailsDto schoolDetailsDto = schoolMapper.map(response);

        log.info("Successfully executed get school by id rest API, response - {}", schoolDetailsDto);
        return schoolDetailsDto;
    }
}

