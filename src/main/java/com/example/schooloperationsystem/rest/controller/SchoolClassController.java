package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.controller.validator.SchoolClassValidator;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.mapper.SchoolClassMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.params.CreateSchoolClassParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/classes")
public class SchoolClassController {

    private final SchoolClassService classService;
    private final SchoolClassMapper classMapper;
    private final SchoolClassValidator schoolClassValidator;

    @GetMapping
    public ResponseEntity<List<SchoolClassDetailsDto>> getClasses() {
        log.info("Executing get all classes rest API");

        List<SchoolClass> response = classService.get();
        ResponseEntity<List<SchoolClassDetailsDto>> responseEntity = ResponseEntity.ok(classMapper.mapList(response));

        log.info("Successfully executed get classes rest API, response entity - {}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<SchoolClassDetailsDto> create(@RequestBody CreateSchoolClassRequestDto requestDto) {
        log.info("Executing create class for the provided request to - {}", requestDto);

        Optional<ErrorType> optionalErrorType = schoolClassValidator.validateCreate(requestDto);

        if (optionalErrorType.isPresent()) {
            SchoolClassDetailsDto schoolClassDetailsDto = new SchoolClassDetailsDto();
            schoolClassDetailsDto.setErrorType(optionalErrorType.get());
            log.info("Executing create class failed, error-{}", optionalErrorType.get());
            return ResponseEntity.ok(schoolClassDetailsDto);
        } else {
            CreateSchoolClassParams params = new CreateSchoolClassParams(
                    requestDto.getClassLetter(),
                    requestDto.getGrade()
            );
            SchoolClass response = classService.add(params);
            ResponseEntity<SchoolClassDetailsDto> responseEntity = ResponseEntity.ok(classMapper.mapToSchoolClassDetailsDto(response));
            log.info("Successfully executed create staff rest API, response entity - {}", responseEntity);
            return responseEntity;
        }
    }
}