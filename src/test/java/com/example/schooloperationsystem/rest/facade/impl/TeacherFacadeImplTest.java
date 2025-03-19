package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.mapper.TeacherMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateTeacherRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;
import com.example.schooloperationsystem.rest.facade.TeacherFacade;
import com.example.schooloperationsystem.rest.facade.validator.TeacherValidator;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateTeacherParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TeacherFacadeImplTest {

    @Mock
    private TeacherService service;

    @Mock
    private TeacherMapper mapper;

    @Mock
    private TeacherValidator teacherValidator;

    private TeacherFacade teacherFacade;

    @BeforeEach
    void setUp() {
        teacherFacade = new TeacherFacadeImpl(service, mapper, teacherValidator);
    }

    @Test
    void getAll() {
        List<Teacher> teachers = new ArrayList<>();
        List<TeacherDetailsDto> teacherDetailsDtos = new ArrayList<>();

        when(service.get()).thenReturn(teachers);
        when(mapper.mapList(teachers)).thenReturn(teacherDetailsDtos);

        List<TeacherDetailsDto> result = teacherFacade.getAll();

        assertEquals(teacherDetailsDtos, result);

        verify(service).get();
        verify(mapper).mapList(teachers);
    }

    @Test
    void create_success() {
        CreateTeacherRequestDto requestDto = new CreateTeacherRequestDto();
        Teacher teacher = new Teacher();
        TeacherDetailsDto teacherDetailsDto = new TeacherDetailsDto();

        CreateTeacherParams params = new CreateTeacherParams(
                1L
        );

        when(teacherValidator.validateCreate(requestDto)).thenReturn(Optional.empty());
        when(mapper.fromRequestDtoToParams(requestDto)).thenReturn(params);
        when(service.create(params)).thenReturn(teacher);
        when(mapper.map(teacher)).thenReturn(teacherDetailsDto);

        TeacherDetailsDto result = teacherFacade.create(requestDto);

        assertEquals(teacherDetailsDto, result);

        verify(teacherValidator).validateCreate(requestDto);
        verify(service).create(params);
        verify(mapper).map(teacher);
    }

    @Test
    void create_MissingStaffId() {
        CreateTeacherRequestDto requestDto = new CreateTeacherRequestDto();

        when(teacherValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_STAFF_ID));

        TeacherDetailsDto result = teacherFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_STAFF_ID, result.getErrorType());

        verify(teacherValidator).validateCreate(requestDto);
    }

    @Test
    void create_StaffNotFound() {
        CreateTeacherRequestDto requestDto = new CreateTeacherRequestDto();

        when(teacherValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.STAFF_NOT_FOUND));

        TeacherDetailsDto result = teacherFacade.create(requestDto);

        assertEquals(ErrorType.STAFF_NOT_FOUND, result.getErrorType());

        verify(teacherValidator).validateCreate(requestDto);
    }

    @Test
    void getByStaffId() {
        Teacher teacher = new Teacher();
        TeacherDetailsDto teacherDetailsDto = new TeacherDetailsDto();

        when(service.findByStaffId(1L)).thenReturn(teacher);
        when(mapper.map(teacher)).thenReturn(teacherDetailsDto);

        TeacherDetailsDto result = teacherFacade.getByStaffId(1L);

        assertEquals(teacherDetailsDto, result);

        verify(service).findByStaffId(1L);
        verify(mapper).map(teacher);
    }

    @Test
    void getByStaffId_TeacherNotFound() {
        when(service.findByStaffId(1L)).thenReturn(null);

        TeacherDetailsDto result = teacherFacade.getByStaffId(1L);

        assertEquals(ErrorType.TEACHER_NOT_FOUND, result.getErrorType());

        verify(service).findByStaffId(1L);
    }
}