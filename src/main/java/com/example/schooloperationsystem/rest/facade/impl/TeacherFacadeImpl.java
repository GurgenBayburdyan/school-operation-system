package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.mapper.TeacherMapper;
import com.example.schooloperationsystem.rest.facade.validator.TeacherValidator;
import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;
import com.example.schooloperationsystem.rest.facade.TeacherFacade;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateTeacherParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
class TeacherFacadeImpl implements TeacherFacade {

    private final TeacherService service;
    private final TeacherMapper mapper;
    private final TeacherValidator teacherValidator;

    @Override
    public List<TeacherDetailsDto> getAll() {
        log.info("Executing get all teachers rest API");

        List<Teacher> response = service.get();

        List<TeacherDetailsDto> teacherDetailsDtos = mapper.mapList(response);
        log.info("Successfully executed get teachers rest API, response - {}", teacherDetailsDtos);
        return teacherDetailsDtos;
    }

    @Override
    public TeacherDetailsDto create(CreateTeacherRequestDto requestDto) {
        log.info("Executing create teacher for the provided request to - {}:", requestDto);

        Optional<ErrorType> optionalErrorType = teacherValidator.validateCreate(requestDto);

        if (optionalErrorType.isPresent()) {
            TeacherDetailsDto teacherDetailsDto = new TeacherDetailsDto(optionalErrorType.get());
            log.info("Executing create teacher failed, error-{}", optionalErrorType.get());
            return teacherDetailsDto;
        } else {
            CreateTeacherParams params = new CreateTeacherParams(
                    requestDto.getStaffId()
            );
            Teacher response = service.create(params);
            TeacherDetailsDto teacherDetailsDto = mapper.mapToTeacherDetailsDto(response);
            log.info("Successfully executed create teacher rest API, response - {}", teacherDetailsDto);
            return teacherDetailsDto;
        }
    }

    @Override
    public TeacherDetailsDto getByStaffId(Long staffId) {
        log.info("Executing get teacher by staff id-{}", staffId);

        Teacher response = service.findByStaffId(staffId);

        TeacherDetailsDto teacherDetailsDto = mapper.mapToTeacherDetailsDto(response);

        log.info("Successfully executed get teacher by staff id rest API, response - {}", teacherDetailsDto);
        return teacherDetailsDto;
    }

}
