package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.dto.response.ErrorType;
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
import java.util.Optional;

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

        //do your best not to mutate objects later, the object should be created and returned immediately 
        HeadMasterDetailsDto headMasterDetailsDto = new HeadMasterDetailsDto();

        //todo please create HeadMasterValidator and move validateCreate there
        Optional<ErrorType> optionalErrorType = validateCreate(requestDto);
        if (optionalErrorType.isPresent()) {
            headMasterDetailsDto.setErrorType(optionalErrorType.get());
        } else {
            SchoolClass schoolClass = schoolClassService.getClassById(requestDto.getTeacherId());
            Teacher teacher = teacherService.getById(requestDto.getTeacherId());

            //todo let's first finish everything with services then do mapping
            SchoolClassDto schoolClassDto = schoolClassMapper.mapToSchoolClassDto(schoolClass);
            TeacherDto teacherDto = teacherMapper.mapToTeacherDto(teacher);

            CreateHeadMasterParams params = new CreateHeadMasterParams(teacherDto, schoolClassDto);

            HeadMaster headMaster = headMasterService.addHeadMaster(params);
            ResponseEntity<HeadMasterDetailsDto> responseEntity = ResponseEntity.ok(headMasterMapper.map(headMaster));

            log.info("Successfully executed create head master rest API, response entity - {}", responseEntity);
            return responseEntity;
        }

        //todo please write a log
        return ResponseEntity.ok(headMasterDetailsDto);
    }

    private Optional<ErrorType> validateCreate(CreateHeadMasterRequestDto requestDto) {
        if (requestDto.getClassId() == null) {
            return Optional.of(ErrorType.MISSING_SCHOOL_CLASS_ID);
        } else if (requestDto.getTeacherId() == null) {
            return Optional.of(ErrorType.MISSING_TEACHER_ID);
        }
        return Optional.empty();
    }
}
