package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.mapper.SchoolClassMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.params.SchoolCreateClassParams;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Gurgen Bayburdyan
 */
@Slf4j
@RestController
@RequestMapping("/classes")
public class SchoolClassController {

    private static final Logger logger = LoggerFactory.getLogger(SchoolClassController.class);

    private final SchoolClassService classService;
    private final SchoolClassMapper classMapper;

    public SchoolClassController(SchoolClassService classService, SchoolClassMapper classMapper) {
        this.classService = classService;
        this.classMapper = classMapper;
    }

    @GetMapping
    public List<SchoolClassDetailsDto> getClasses() {
        logger.info("Getting all classes");
        List<SchoolClass> response = classService.getClasses();
        logger.info("Got classes {}", response);
        return classMapper.mapList(response);
    }

    @PostMapping
    public SchoolClassDetailsDto createClass(@RequestBody CreateSchoolClassRequestDto requestDto) {
        logger.info("Creating a new class with letter: {} and grade: {}", requestDto.getClassLetter(), requestDto.getGrade());

        SchoolCreateClassParams params = new SchoolCreateClassParams();
        params.setClassLetter(requestDto.getClassLetter());
        params.setGrade(requestDto.getGrade());

        SchoolClass response = classService.addClass(params);
        logger.info("Created class: {}", response);

        return classMapper.map(response);
    }
}