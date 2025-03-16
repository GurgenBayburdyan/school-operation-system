package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.School;
import com.example.schooloperationsystem.mapper.SchoolMapper;
import com.example.schooloperationsystem.rest.facade.validator.SchoolValidator;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.SchoolDetailsDto;
import com.example.schooloperationsystem.rest.facade.SchoolFacade;
import com.example.schooloperationsystem.service.SchoolService;
import com.example.schooloperationsystem.service.params.CreateSchoolParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
class SchoolFacadeImpl implements SchoolFacade {

    private final SchoolService schoolService;
    private final SchoolMapper schoolMapper;
    private final SchoolValidator schoolValidator;

    @Override
    public SchoolDetailsDto create(CreateSchoolRequestDto requestDto) {
        log.debug("Executing create school for the provided request to - {}", requestDto);

        final Optional<ErrorType> optionalErrorType = schoolValidator.validateCreate(requestDto);

        if (optionalErrorType.isPresent()) {
            SchoolDetailsDto schoolClassDetailsDto = new SchoolDetailsDto(optionalErrorType.get());
            log.info("Executing create school failed, error-{}", optionalErrorType.get());
            return schoolClassDetailsDto;
        }

        final CreateSchoolParams params = new CreateSchoolParams(
                requestDto.getNumber(),
                requestDto.getNamedAfter(),
                requestDto.getAddress(),
                requestDto.getPhotoUrl()
        );

        final School response = schoolService.create(params);
        final SchoolDetailsDto schoolDetailsDto = schoolMapper.map(response);

        log.debug("Successfully executed create school rest API, response - {}", schoolDetailsDto);
        return schoolDetailsDto;
    }

    @Override
    public List<SchoolDetailsDto> get() {
        log.debug("Executing get all schools rest API");

        final List<School> schoolList = schoolService.get();
        final List<SchoolDetailsDto> schoolDetailsDtos = schoolMapper.mapList(schoolList);

        log.debug("Successfully executed get schools rest API, response - {}", schoolDetailsDtos);
        return schoolDetailsDtos;
    }

    @Override
    public SchoolDetailsDto getById(Long id) {
        log.info("Executing get school by id-{}", id);

        School response = schoolService.findById(id);

        SchoolDetailsDto schoolDetailsDto = schoolMapper.map(response);

        log.info("Successfully executed get school by id rest API, response - {}", schoolDetailsDto);
        return schoolDetailsDto;
    }
}

