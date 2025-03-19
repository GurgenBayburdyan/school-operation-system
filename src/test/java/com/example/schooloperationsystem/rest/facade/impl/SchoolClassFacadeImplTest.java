package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.mapper.SchoolClassMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;
import com.example.schooloperationsystem.rest.facade.SchoolClassFacade;
import com.example.schooloperationsystem.rest.facade.validator.SchoolClassValidator;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.params.CreateSchoolClassParams;
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
class SchoolClassFacadeImplTest {
    @Mock
    private SchoolClassService classService;

    @Mock
    private SchoolClassMapper classMapper;

    @Mock
    private SchoolClassValidator schoolClassValidator;

    private SchoolClassFacade schoolClassFacade;

    @BeforeEach
    void setUp() {
        schoolClassFacade = new SchoolClassFacadeImpl(classService, classMapper, schoolClassValidator);
    }

    @Test
    void getAll() {
        List<SchoolClass> schoolClasses = new ArrayList<>();
        List<SchoolClassDetailsDto> schoolClassDetailsDtos = new ArrayList<>();

        when(classService.get()).thenReturn(schoolClasses);
        when(classMapper.mapList(schoolClasses)).thenReturn(schoolClassDetailsDtos);

        List<SchoolClassDetailsDto> result = schoolClassFacade.getAll();

        assertEquals(schoolClassDetailsDtos, result);

        verify(classService).get();
        verify(classMapper).mapList(schoolClasses);
    }

    @Test
    void create_success() {
        CreateSchoolClassRequestDto requestDto = new CreateSchoolClassRequestDto();
        SchoolClass schoolClass = new SchoolClass();
        SchoolClassDetailsDto schoolClassDetailsDto = new SchoolClassDetailsDto();

        CreateSchoolClassParams params = new CreateSchoolClassParams(
                'a',
                1,
                1L
        );

        when(schoolClassValidator.validateCreate(requestDto)).thenReturn(Optional.empty());
        when(classMapper.fromRequestDtoToParams(requestDto)).thenReturn(params);
        when(classService.add(params)).thenReturn(schoolClass);
        when(classMapper.map(schoolClass)).thenReturn(schoolClassDetailsDto);

        SchoolClassDetailsDto result = schoolClassFacade.create(requestDto);

        assertEquals(schoolClassDetailsDto, result);

        verify(schoolClassValidator).validateCreate(requestDto);
        verify(classService).add(params);
        verify(classMapper).map(schoolClass);
    }

    @Test
    void create_MissingClassLetter() {
        CreateSchoolClassRequestDto requestDto = new CreateSchoolClassRequestDto();

        when(schoolClassValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_CLASS_LETTER));

        SchoolClassDetailsDto result = schoolClassFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_CLASS_LETTER, result.getErrorType());

        verify(schoolClassValidator).validateCreate(requestDto);
    }

    @Test
    void create_MissingGrade() {
        CreateSchoolClassRequestDto requestDto = new CreateSchoolClassRequestDto();

        when(schoolClassValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_GRADE));

        SchoolClassDetailsDto result = schoolClassFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_GRADE, result.getErrorType());

        verify(schoolClassValidator).validateCreate(requestDto);
    }

    @Test
    void create_MissingSchoolId() {
        CreateSchoolClassRequestDto requestDto = new CreateSchoolClassRequestDto();

        when(schoolClassValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_SCHOOL_ID));

        SchoolClassDetailsDto result = schoolClassFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_SCHOOL_ID, result.getErrorType());

        verify(schoolClassValidator).validateCreate(requestDto);
    }

    @Test
    void create_SchoolNotFound() {
        CreateSchoolClassRequestDto requestDto = new CreateSchoolClassRequestDto();

        when(schoolClassValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.SCHOOL_NOT_FOUND));

        SchoolClassDetailsDto result = schoolClassFacade.create(requestDto);

        assertEquals(ErrorType.SCHOOL_NOT_FOUND, result.getErrorType());

        verify(schoolClassValidator).validateCreate(requestDto);
    }

    @Test
    void getById_success() {
        SchoolClass schoolClass = new SchoolClass();
        SchoolClassDetailsDto schoolClassDetailsDto = new SchoolClassDetailsDto();

        when(classService.findById(1L)).thenReturn(schoolClass);
        when(classMapper.map(schoolClass)).thenReturn(schoolClassDetailsDto);

        SchoolClassDetailsDto result = schoolClassFacade.getById(1L);

        assertEquals(schoolClassDetailsDto, result);

        verify(classService).findById(1L);
        verify(classMapper).map(schoolClass);
    }

    @Test
    void getById_ClassNotFound() {
        when(classService.findById(1L)).thenReturn(null);

        SchoolClassDetailsDto result = schoolClassFacade.getById(1L);

        assertEquals(ErrorType.CLASS_NOT_FOUND, result.getErrorType());

        verify(classService).findById(1L);
    }

    @Test
    void getBySchoolId() {
        List<SchoolClass> schoolClasses = new ArrayList<>();
        List<SchoolClassDetailsDto> schoolClassDetailsDtos = new ArrayList<>();

        when(classService.findBySchoolId(1L)).thenReturn(schoolClasses);
        when(classMapper.mapList(schoolClasses)).thenReturn(schoolClassDetailsDtos);

        List<SchoolClassDetailsDto> result = schoolClassFacade.getBySchoolId(1L);

        assertEquals(schoolClassDetailsDtos, result);

        verify(classService).findBySchoolId(1L);
        verify(classMapper).mapList(schoolClasses);
    }
}