package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.mapper.SchoolClassMapper;
import com.example.schooloperationsystem.rest.controller.validator.SchoolClassValidator;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;
import com.example.schooloperationsystem.rest.facade.SchoolClassFacade;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.params.CreateSchoolClassParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SchoolClassFacadeImpl implements SchoolClassFacade {

    private final SchoolClassService classService;
    private final SchoolClassMapper classMapper;
    private final SchoolClassValidator schoolClassValidator;

    @Override
    public List<SchoolClassDetailsDto> getClasses() {
        log.info("Executing get all classes rest API");

        List<SchoolClass> response = classService.get();
        List<SchoolClassDetailsDto> schoolClassDetailsDtos = classMapper.mapList(response);

        log.info("Successfully executed get classes rest API, response  - {}", schoolClassDetailsDtos);
        return schoolClassDetailsDtos;
    }

    @Override
    public SchoolClassDetailsDto createClass(CreateSchoolClassRequestDto requestDto) {
        log.info("Executing create class for the provided request to - {}", requestDto);

        Optional<ErrorType> optionalErrorType = schoolClassValidator.validateCreate(requestDto);

        if (optionalErrorType.isPresent()) {
            SchoolClassDetailsDto schoolClassDetailsDto = new SchoolClassDetailsDto();
            schoolClassDetailsDto.setErrorType(optionalErrorType.get());
            log.info("Executing create class failed, error-{}", optionalErrorType.get());
            return schoolClassDetailsDto;
        } else {
            CreateSchoolClassParams params = new CreateSchoolClassParams(
                    requestDto.getClassLetter(),
                    requestDto.getGrade(),
                    requestDto.getSchoolId()
            );
            SchoolClass response = classService.add(params);
            SchoolClassDetailsDto schoolClassDetailsDto = classMapper.mapToSchoolClassDetailsDto(response);

            log.info("Successfully executed create class rest API, response - {}", schoolClassDetailsDto);
            return schoolClassDetailsDto;
        }
    }

    @Override
    public SchoolClassDetailsDto getClassById(Long id) {
        log.info("Executing get class by id, id-{}", id);

        SchoolClass schoolClass = classService.getById(id);

        if (schoolClass == null) {
            SchoolClassDetailsDto schoolClassDetailsDto = new SchoolClassDetailsDto();
            schoolClassDetailsDto.setErrorType(ErrorType.CLASS_NOT_FOUND);
            log.info("Executing get class by id failed, error-{}", schoolClassDetailsDto.getErrorType());
            return schoolClassDetailsDto;
        }

        SchoolClassDetailsDto schoolClassDetailsDto = classMapper.mapToSchoolClassDetailsDto(schoolClass);

        log.info("Successfully executed get class by id rest API, response - {}", schoolClassDetailsDto);
        return schoolClassDetailsDto;
    }

    @Override
    public List<SchoolClassDetailsDto> getClassesBySchoolId(Long schoolId) {
        log.info("Executing get classes by school id, school id-{}", schoolId);

        List<SchoolClass> schoolClasses = classService.getBySchoolId(schoolId);

        List<SchoolClassDetailsDto> schoolClassDetailsDtos = classMapper.mapList(schoolClasses);

        log.info("Successfully executed get classes by school id rest API, response - {}", schoolClassDetailsDtos);
        return schoolClassDetailsDtos;
    }
}
