package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.mapper.PupilMapper;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import com.example.schooloperationsystem.rest.facade.PupilFacade;
import com.example.schooloperationsystem.rest.facade.validator.PupilValidator;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.params.CreatePupilParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PupilFacadeImplTest {

    @Mock
    private PupilService pupilService;

    @Mock
    private PupilMapper pupilMapper;

    @Mock
    private PupilValidator pupilValidator;

    private PupilFacade pupilFacade;

    @BeforeEach
    void setUp() {
        pupilFacade = new PupilFacadeImpl(pupilService, pupilMapper, pupilValidator);
    }

    @Test
    void getAll() {
        List<Pupil> pupils = new ArrayList<>();
        List<PupilDetailsDto> pupilDetailsDtos = new ArrayList<>();

        when(pupilService.get()).thenReturn(pupils);
        when(pupilMapper.mapList(pupils)).thenReturn(pupilDetailsDtos);

        List<PupilDetailsDto> result = pupilFacade.getAll();

        assertEquals(pupilDetailsDtos, result);

        verify(pupilService).get();
        verify(pupilMapper).mapList(pupils);
    }

    @Test
    void create_success() {
        CreatePupilRequestDto requestDto = new CreatePupilRequestDto();
        Pupil pupil = new Pupil();
        PupilDetailsDto pupilDetailsDto = new PupilDetailsDto();

        CreatePupilParams params = new CreatePupilParams(
                "firstName",
                "lastName",
                LocalDateTime.now(),
                1L
        );

        when(pupilValidator.validateCreate(requestDto)).thenReturn(Optional.empty());
        when(pupilMapper.fromRequestDtoToParams(requestDto)).thenReturn(params);
        when(pupilService.add(params)).thenReturn(pupil);
        when(pupilMapper.map(pupil)).thenReturn(pupilDetailsDto);

        PupilDetailsDto result = pupilFacade.create(requestDto);

        assertEquals(pupilDetailsDto, result);

        verify(pupilValidator).validateCreate(requestDto);
        verify(pupilService).add(params);
        verify(pupilMapper).map(pupil);
    }

    @Test
    void create_MissingFirstName() {
        CreatePupilRequestDto requestDto = new CreatePupilRequestDto();

        when(pupilValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_FIRST_NAME));

        PupilDetailsDto result = pupilFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_FIRST_NAME, result.getErrorType());

        verify(pupilValidator).validateCreate(requestDto);
    }

    @Test
    void create_MissingLastName() {
        CreatePupilRequestDto requestDto = new CreatePupilRequestDto();

        when(pupilValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_LAST_NAME));

        PupilDetailsDto result = pupilFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_LAST_NAME, result.getErrorType());

        verify(pupilValidator).validateCreate(requestDto);
    }

    @Test
    void create_MissingDateOfBirth() {
        CreatePupilRequestDto requestDto = new CreatePupilRequestDto();

        when(pupilValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_DATE_OF_BIRTH));

        PupilDetailsDto result = pupilFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_DATE_OF_BIRTH, result.getErrorType());

        verify(pupilValidator).validateCreate(requestDto);
    }

    @Test
    void create_MissingSchoolId() {
        CreatePupilRequestDto requestDto = new CreatePupilRequestDto();

        when(pupilValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_SCHOOL_ID));

        PupilDetailsDto result = pupilFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_SCHOOL_ID, result.getErrorType());

        verify(pupilValidator).validateCreate(requestDto);
    }

    @Test
    void create_SchoolNotFound() {
        CreatePupilRequestDto requestDto = new CreatePupilRequestDto();

        when(pupilValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.SCHOOL_NOT_FOUND));

        PupilDetailsDto result = pupilFacade.create(requestDto);

        assertEquals(ErrorType.SCHOOL_NOT_FOUND, result.getErrorType());

        verify(pupilValidator).validateCreate(requestDto);
    }

    @Test
    void findBySchoolId() {
        List<Pupil> pupils = new ArrayList<>();
        List<PupilDetailsDto> pupilDetailsDtos = new ArrayList<>();

        when(pupilService.findBySchoolId(1L)).thenReturn(pupils);
        when(pupilMapper.mapList(pupils)).thenReturn(pupilDetailsDtos);

        List<PupilDetailsDto> result = pupilFacade.getBySchoolId(1L);

        assertEquals(pupilDetailsDtos, result);

        verify(pupilService).findBySchoolId(1L);
        verify(pupilMapper).mapList(pupils);
    }

    @Test
    void deleteById_success() {
        Pupil pupil = new Pupil();
        PupilDetailsDto pupilDetailsDto = new PupilDetailsDto();

        when(pupilService.deleteById(1L)).thenReturn(pupil);
        when(pupilMapper.map(pupil)).thenReturn(pupilDetailsDto);

        PupilDetailsDto result = pupilFacade.deleteById(1L);

        assertEquals(pupilDetailsDto, result);

        verify(pupilService).deleteById(1L);
        verify(pupilMapper).map(pupil);
    }

    @Test
    void deleteById_PupilNotFound() {
        when(pupilService.deleteById(1L)).thenReturn(null);

        PupilDetailsDto result = pupilFacade.deleteById(1L);

        assertEquals(ErrorType.PUPIL_NOT_FOUND, result.getErrorType());

        verify(pupilService).deleteById(1L);
    }

}