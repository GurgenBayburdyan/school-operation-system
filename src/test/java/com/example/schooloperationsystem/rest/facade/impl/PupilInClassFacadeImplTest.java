package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.mapper.PupilInClassMapper;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.PupilInClassDetailsDto;
import com.example.schooloperationsystem.rest.facade.PupilInClassFacade;
import com.example.schooloperationsystem.rest.facade.validator.PupilInClassValidator;
import com.example.schooloperationsystem.service.PupilInClassService;
import com.example.schooloperationsystem.service.params.CreatePupilInClassParams;
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
class PupilInClassFacadeImplTest {

    @Mock
    private PupilInClassService pupilInClassService;

    @Mock
    private PupilInClassMapper pupilInClassMapper;

    @Mock
    private PupilInClassValidator pupilInClassValidator;

    private PupilInClassFacade pupilInClassFacade;

    @BeforeEach
    void setUp() {
        pupilInClassFacade = new PupilInClassFacadeImpl(pupilInClassService, pupilInClassMapper, pupilInClassValidator);
    }

    @Test
    void getAll() {
        List<PupilInClass> pupilInClasses = new ArrayList<>();
        List<PupilInClassDetailsDto> pupilInClassDetailsDtos = new ArrayList<>();

        when(pupilInClassService.get()).thenReturn(pupilInClasses);
        when(pupilInClassMapper.mapList(pupilInClasses)).thenReturn(pupilInClassDetailsDtos);

        List<PupilInClassDetailsDto> result = pupilInClassFacade.getAll();

        assertEquals(pupilInClassDetailsDtos, result);

        verify(pupilInClassService).get();
        verify(pupilInClassMapper).mapList(pupilInClasses);
    }

    @Test
    void create_success() {
        CreatePupilInClassRequestDto requestDto = new CreatePupilInClassRequestDto();
        PupilInClass pupilInClass = new PupilInClass();
        PupilInClassDetailsDto pupilInClassDetailsDto = new PupilInClassDetailsDto();

        CreatePupilInClassParams params = new CreatePupilInClassParams(
                1L,
                1L
        );

        when(pupilInClassValidator.validateCreate(requestDto)).thenReturn(Optional.empty());
        when(pupilInClassMapper.fromRequestDtoToParams(requestDto)).thenReturn(params);
        when(pupilInClassService.add(params)).thenReturn(pupilInClass);
        when(pupilInClassMapper.map(pupilInClass)).thenReturn(pupilInClassDetailsDto);

        PupilInClassDetailsDto result = pupilInClassFacade.create(requestDto);

        assertEquals(pupilInClassDetailsDto, result);

        verify(pupilInClassValidator).validateCreate(requestDto);
        verify(pupilInClassService).add(params);
        verify(pupilInClassMapper).map(pupilInClass);
    }

    @Test
    void create_MissingSchoolClassId() {
        CreatePupilInClassRequestDto requestDto = new CreatePupilInClassRequestDto();

        when(pupilInClassValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_SCHOOL_CLASS_ID));

        PupilInClassDetailsDto result = pupilInClassFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_SCHOOL_CLASS_ID, result.getErrorType());

        verify(pupilInClassValidator).validateCreate(requestDto);
    }

    @Test
    void create_SchoolClassNotFound() {
        CreatePupilInClassRequestDto requestDto = new CreatePupilInClassRequestDto();

        when(pupilInClassValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.SCHOOL_NOT_FOUND));

        PupilInClassDetailsDto result = pupilInClassFacade.create(requestDto);

        assertEquals(ErrorType.SCHOOL_NOT_FOUND, result.getErrorType());

        verify(pupilInClassValidator).validateCreate(requestDto);
    }

    @Test
    void create_MissingPupilId() {
        CreatePupilInClassRequestDto requestDto = new CreatePupilInClassRequestDto();

        when(pupilInClassValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_PUPIL_ID));

        PupilInClassDetailsDto result = pupilInClassFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_PUPIL_ID, result.getErrorType());

        verify(pupilInClassValidator).validateCreate(requestDto);
    }

    @Test
    void create_PupilNotFound() {
        CreatePupilInClassRequestDto requestDto = new CreatePupilInClassRequestDto();

        when(pupilInClassValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.PUPIL_NOT_FOUND));

        PupilInClassDetailsDto result = pupilInClassFacade.create(requestDto);

        assertEquals(ErrorType.PUPIL_NOT_FOUND, result.getErrorType());

        verify(pupilInClassValidator).validateCreate(requestDto);
    }

    @Test
    void getByClassId() {
        List<PupilInClass> pupilInClasses = new ArrayList<>();
        List<PupilInClassDetailsDto> pupilInClassDetailsDtos = new ArrayList<>();

        when(pupilInClassService.findBySchoolClassId(1L)).thenReturn(pupilInClasses);
        when(pupilInClassMapper.mapList(pupilInClasses)).thenReturn(pupilInClassDetailsDtos);

        List<PupilInClassDetailsDto> result = pupilInClassFacade.getByClassId(1L);

        assertEquals(pupilInClassDetailsDtos, result);

        verify(pupilInClassService).findBySchoolClassId(1L);
        verify(pupilInClassMapper).mapList(pupilInClasses);
    }

    @Test
    void deleteByPupilId_success() {
        PupilInClass pupilInClasses = new PupilInClass();
        PupilInClassDetailsDto pupilInClassDetailsDto = new PupilInClassDetailsDto();

        when(pupilInClassService.deleteByPupilId(1L)).thenReturn(pupilInClasses);
        when(pupilInClassMapper.map(pupilInClasses)).thenReturn(pupilInClassDetailsDto);

        PupilInClassDetailsDto result = pupilInClassFacade.deleteByPupilId(1L);

        assertEquals(pupilInClassDetailsDto, result);

        verify(pupilInClassService).deleteByPupilId(1L);
        verify(pupilInClassMapper).map(pupilInClasses);
    }

    @Test
    void deleteByPupilId_PupilNotFound() {
        when(pupilInClassService.deleteByPupilId(1L)).thenReturn(null);

        PupilInClassDetailsDto result = pupilInClassFacade.deleteByPupilId(1L);

        assertEquals(ErrorType.PUPIL_NOT_FOUND, result.getErrorType());

        verify(pupilInClassService).deleteByPupilId(1L);
    }

    @Test
    void getByPupilId_success() {
        PupilInClass pupilInClass = new PupilInClass();
        PupilInClassDetailsDto pupilInClassDetailsDto = new PupilInClassDetailsDto();

        when(pupilInClassService.findByPupilId(1L)).thenReturn(pupilInClass);
        when(pupilInClassMapper.map(pupilInClass)).thenReturn(pupilInClassDetailsDto);

        PupilInClassDetailsDto result = pupilInClassFacade.getByPupilId(1L);

        assertEquals(pupilInClassDetailsDto, result);

        verify(pupilInClassService).findByPupilId(1L);
        verify(pupilInClassMapper).map(pupilInClass);
    }

    @Test
    void getByPupilId_PupilNotFound() {
        when(pupilInClassService.findByPupilId(1L)).thenReturn(null);

        PupilInClassDetailsDto pupilInClassDetailsDto = pupilInClassFacade.getByPupilId(1L);

        assertEquals(ErrorType.PUPIL_IN_CLASS_NOT_FOUND, pupilInClassDetailsDto.getErrorType());

        verify(pupilInClassService).findByPupilId(1L);
    }

}