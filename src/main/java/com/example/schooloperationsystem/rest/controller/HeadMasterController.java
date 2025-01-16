package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.mapper.HeadMasterMapper;
import com.example.schooloperationsystem.rest.dto.SchoolClassDto;
import com.example.schooloperationsystem.rest.dto.StaffDto;
import com.example.schooloperationsystem.rest.dto.TeacherDto;
import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.HeadMasterDetailsDto;
import com.example.schooloperationsystem.service.HeadMasterService;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateHeadMasterParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/headmasters")
@AllArgsConstructor
public class HeadMasterController {

    private static final Logger logger = LoggerFactory.getLogger(HeadMasterController.class);

    private final HeadMasterService service;
    private final HeadMasterMapper mapper;
    private final SchoolClassService schoolClassService;
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<HeadMasterDetailsDto>> getAllHeadMasters() {
        logger.info("Executing get all head masters rest API");

        List<HeadMaster> response = service.getHeadMasters();

        logger.info("Successfully executed get all head masters rest API, response entity - {}", response);
        return ResponseEntity.ok(mapper.mapList(response));
    }

    @PostMapping
    public HeadMasterDetailsDto create(@RequestBody CreateHeadMasterRequestDto requestDto) {
        logger.info("Executing create head master for the provided request to - {}:", requestDto);
        CreateHeadMasterParams params = new CreateHeadMasterParams();

        SchoolClass schoolClass = schoolClassService.getClassById(requestDto.getTeacherId());
        Teacher teacher = teacherService.getById(requestDto.getTeacherId());

        SchoolClassDto schoolClassDto = new SchoolClassDto();
        schoolClassDto.setClassLetter(schoolClass.getLetter());
        schoolClassDto.setGrade(schoolClass.getGrade());

        TeacherDto teacherDto = new TeacherDto();
        Staff staff = teacher.getStaff();
        StaffDto staffDto = new StaffDto();
        staffDto.setFirstName(staff.getFirstName());
        staffDto.setLastName(staff.getLastName());
        staffDto.setDateOfBirth(staff.getDateOfBirth());
        teacherDto.setStaffDto(staffDto);

        params.setTeacherDto(teacherDto);
        params.setSchoolClassDto(schoolClassDto);

        HeadMaster headMaster = service.addHeadMaster(params);
        logger.info("Successfully executed create head master rest API, response entity - {}", headMaster);
        return mapper.map(headMaster);
    }
}
