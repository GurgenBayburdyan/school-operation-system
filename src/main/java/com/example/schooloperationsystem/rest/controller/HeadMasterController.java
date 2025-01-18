package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.mapper.HeadMasterMapper;
import com.example.schooloperationsystem.mapper.SchoolClassMapper;
import com.example.schooloperationsystem.mapper.TeacherMapper;
import com.example.schooloperationsystem.rest.dto.SchoolClassDto;
import com.example.schooloperationsystem.rest.dto.TeacherDto;
import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.HeadMasterDetailsDto;
import com.example.schooloperationsystem.service.HeadMasterService;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateHeadMasterParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/headmasters")
@AllArgsConstructor
public class HeadMasterController {

    private final HeadMasterService headMasterService;
    private final HeadMasterMapper headMasterMapper;
    private final SchoolClassService schoolClassService;
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;
    private final SchoolClassMapper schoolClassMapper;

    @GetMapping
    public ResponseEntity<List<HeadMasterDetailsDto>> getAllHeadMasters() {
        log.info("Executing get all head masters rest API");

        List<HeadMaster> response = headMasterService.getHeadMasters();
        ResponseEntity<List<HeadMasterDetailsDto>> responseEntity = ResponseEntity.ok(headMasterMapper.mapList(response));

        log.info("Successfully executed get all head masters rest API, response entity - {}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<HeadMasterDetailsDto> create(@RequestBody CreateHeadMasterRequestDto requestDto) {
        log.info("Executing create head master for the provided request to - {}:", requestDto);

        SchoolClass schoolClass = schoolClassService.getClassById(requestDto.getTeacherId());
        Teacher teacher = teacherService.getById(requestDto.getTeacherId());

        SchoolClassDto schoolClassDto = schoolClassMapper.mapToSchoolClassDto(schoolClass);
        TeacherDto teacherDto = teacherMapper.mapToTeacherDto(teacher);

        CreateHeadMasterParams params = new CreateHeadMasterParams(teacherDto, schoolClassDto);

        HeadMaster headMaster = headMasterService.addHeadMaster(params);
        ResponseEntity<HeadMasterDetailsDto> responseEntity = ResponseEntity.ok(headMasterMapper.map(headMaster));

        log.info("Successfully executed create head master rest API, response entity - {}", responseEntity);
        return responseEntity;
    }
}