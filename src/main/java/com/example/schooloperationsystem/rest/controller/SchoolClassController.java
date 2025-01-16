package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.mapper.SchoolClassMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.params.CreateSchoolClassParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/classes")
public class SchoolClassController {

    private static final Logger logger = LoggerFactory.getLogger(SchoolClassController.class);

    private final SchoolClassService classService;
    private final SchoolClassMapper classMapper;

    @GetMapping
    public List<SchoolClassDetailsDto> getClasses() {
        logger.info("Executing get all classes rest API");

        List<SchoolClass> response = classService.getClasses();

        logger.info("Successfully executed get classes rest API, response entity - {}", response);
        return classMapper.mapList(response);
    }

    @PostMapping
    public ResponseEntity<SchoolClassDetailsDto> create(@RequestBody CreateSchoolClassRequestDto requestDto) {
        logger.info("Executing create class for the provided request to - {}:", requestDto);

        CreateSchoolClassParams params = new CreateSchoolClassParams();
        params.setClassLetter(requestDto.getClassLetter());
        params.setGrade(requestDto.getGrade());

        SchoolClass response = classService.addClass(params);

        logger.info("Successfully executed create class rest API, response entity - {}", response);

        return ResponseEntity.ok(classMapper.map(response));
    }
}
