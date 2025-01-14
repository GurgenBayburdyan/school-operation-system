package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.Class;
import com.example.schooloperationsystem.mapper.ClassMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ClassDetailsDto;
import com.example.schooloperationsystem.service.ClassService;
import com.example.schooloperationsystem.service.params.CreateClassParams;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Gurgen Bayburdyan
 */
@RestController
@RequestMapping("/classes")
public class ClassController {

    private final ClassService classService;
    private final ClassMapper classMapper;

    public ClassController(ClassService classService, ClassMapper classMapper) {
        this.classService = classService;
        this.classMapper = classMapper;
    }

    @GetMapping
    public List<ClassDetailsDto> getClasses() {
        List<Class> response = classService.getClasses();
        return classMapper.mapList(response);
    }

    @PostMapping
    public ClassDetailsDto getClasses(@RequestBody CreateClassRequestDto requestDto) {
        CreateClassParams params = new CreateClassParams();
        params.setClassLetter(requestDto.getClassLetter());
        params.setGrade(requestDto.getGrade());
        Class response = classService.addClass(params);
        return classMapper.map(response);
    }
}

