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
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/headmasters")
public class HeadMasterController {

    private static final Logger logger = LoggerFactory.getLogger(HeadMasterController.class);

    private final HeadMasterService service;
    private final HeadMasterMapper mapper;
    private final SchoolClassService schoolClassService;
    private final TeacherService teacherService;

    public HeadMasterController(HeadMasterService service, HeadMasterMapper mapper, SchoolClassService schoolClassService, TeacherService teacherService) {
        this.service = service;
        this.mapper = mapper;
        this.schoolClassService = schoolClassService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<HeadMasterDetailsDto> getAllHeadMasters() {
        logger.info("Getting HeadMasters...");
        List<HeadMaster> headMasters = service.getHeadMasters();
        logger.info("Got HeadMasters {}", headMasters);
        return mapper.mapList(headMasters);
    }

    @PostMapping
    public HeadMasterDetailsDto createHeadMaster(@RequestBody CreateHeadMasterRequestDto requestDto) {
        logger.info("Creating HeadMaster with teacherId: {} and classId: {}",
                requestDto.getTeacherId(),
                requestDto.getClassId());
        CreateHeadMasterParams params = new CreateHeadMasterParams();

        SchoolClass schoolClass=schoolClassService.getClassById(requestDto.getTeacherId());
        SchoolClassDto schoolClassDto=new SchoolClassDto();
        schoolClassDto.setClassLetter(schoolClass.getLetter());
        schoolClassDto.setGrade(schoolClass.getGrade());

        Teacher teacher=teacherService.getTeacherById(requestDto.getTeacherId());
        TeacherDto teacherDto=new TeacherDto();
        Staff staff=teacher.getStaff();
        StaffDto staffDto=new StaffDto();
        staffDto.setFirstName(staff.getFirstName());
        staffDto.setLastName(staff.getLastName());
        staffDto.setDateOfBirth(staff.getDateOfBirth());
        teacherDto.setStaffDto(staffDto);

        params.setTeacherDto(teacherDto);
        params.setSchoolClassDto(schoolClassDto);

        HeadMaster headMaster = service.addHeadMaster(params);
        logger.info("Created HeadMaster {}", headMaster);
        return mapper.map(headMaster);
    }
}
