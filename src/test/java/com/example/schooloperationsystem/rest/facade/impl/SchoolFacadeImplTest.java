package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.School;
import com.example.schooloperationsystem.mapper.SchoolMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.SchoolDetailsDto;
import com.example.schooloperationsystem.rest.facade.SchoolFacade;
import com.example.schooloperationsystem.rest.facade.validator.SchoolValidator;
import com.example.schooloperationsystem.service.SchoolService;
import com.example.schooloperationsystem.service.params.CreateSchoolParams;
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
class SchoolFacadeImplTest {

    @Mock
    private SchoolService schoolService;

    @Mock
    private SchoolMapper schoolMapper;

    @Mock
    private SchoolValidator schoolValidator;

    private SchoolFacade schoolFacade;

    @BeforeEach
    void setUp() {
        schoolFacade = new SchoolFacadeImpl(schoolService, schoolMapper, schoolValidator);
    }

    @Test
    void get() {
        List<School> schools = new ArrayList<>();
        List<SchoolDetailsDto> schoolDetailsDtos = new ArrayList<>();

        when(schoolService.get()).thenReturn(schools);
        when(schoolMapper.mapList(schools)).thenReturn(schoolDetailsDtos);

        List<SchoolDetailsDto> result = schoolFacade.get();

        assertEquals(schoolDetailsDtos, result);

        verify(schoolService).get();
        verify(schoolMapper).mapList(schools);
    }

    @Test
    void create_success() {
        CreateSchoolRequestDto requestDto = new CreateSchoolRequestDto();
        School school = new School();
        SchoolDetailsDto schoolDetailsDto = new SchoolDetailsDto();

        CreateSchoolParams params = new CreateSchoolParams(
                1,
                "gurgen",
                "address",
                "no photo"
        );

        when(schoolValidator.validateCreate(requestDto)).thenReturn(Optional.empty());
        when(schoolMapper.fromRequestDtoToParams(requestDto)).thenReturn(params);
        when(schoolService.create(params)).thenReturn(school);
        when(schoolMapper.map(school)).thenReturn(schoolDetailsDto);

        SchoolDetailsDto result = schoolFacade.create(requestDto);

        assertEquals(schoolDetailsDto, result);

        verify(schoolValidator).validateCreate(requestDto);
        verify(schoolService).create(params);
        verify(schoolMapper).map(school);
    }

    @Test
    void create_MissingNumber() {
        CreateSchoolRequestDto requestDto = new CreateSchoolRequestDto();

        when(schoolValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_NUMBER));

        SchoolDetailsDto result = schoolFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_NUMBER, result.getErrorType());

        verify(schoolValidator).validateCreate(requestDto);
    }

    @Test
    void create_MissingNamedAfter() {
        CreateSchoolRequestDto requestDto = new CreateSchoolRequestDto();

        when(schoolValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_NAMED_AFTER));

        SchoolDetailsDto result = schoolFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_NAMED_AFTER, result.getErrorType());

        verify(schoolValidator).validateCreate(requestDto);
    }

    @Test
    void create_MissingAddress() {
        CreateSchoolRequestDto requestDto = new CreateSchoolRequestDto();

        when(schoolValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_ADDRESS));

        SchoolDetailsDto result = schoolFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_ADDRESS, result.getErrorType());

        verify(schoolValidator).validateCreate(requestDto);
    }

    @Test
    void create_MissingPhotoUrl() {
        CreateSchoolRequestDto requestDto = new CreateSchoolRequestDto();

        when(schoolValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_PHOTO_URL));

        SchoolDetailsDto result = schoolFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_PHOTO_URL, result.getErrorType());

        verify(schoolValidator).validateCreate(requestDto);
    }

    @Test
    void getById_success() {
        School school = new School();
        SchoolDetailsDto schoolDetailsDto = new SchoolDetailsDto();

        when(schoolService.findById(1L)).thenReturn(school);
        when(schoolMapper.map(school)).thenReturn(schoolDetailsDto);

        SchoolDetailsDto result = schoolFacade.getById(1L);

        assertEquals(schoolDetailsDto, result);

        verify(schoolService).findById(1L);
        verify(schoolMapper).map(school);
    }

    @Test
    void getById_SchoolNotFound() {
        when(schoolService.findById(1L)).thenReturn(null);

        SchoolDetailsDto result = schoolFacade.getById(1L);

        assertEquals(ErrorType.SCHOOL_NOT_FOUND, result.getErrorType());

        verify(schoolService).findById(1L);
    }
}