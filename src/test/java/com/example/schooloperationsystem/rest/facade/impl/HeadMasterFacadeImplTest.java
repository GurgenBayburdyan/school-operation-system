package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.mapper.HeadMasterMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.HeadMasterDetailsDto;
import com.example.schooloperationsystem.rest.facade.validator.HeadMasterValidator;
import com.example.schooloperationsystem.service.HeadMasterService;
import com.example.schooloperationsystem.service.params.CreateHeadMasterParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeadMasterFacadeImplTest {

    @Mock
    private HeadMasterService headMasterService;

    @Mock
    private HeadMasterMapper headMasterMapper;

    @Mock
    private HeadMasterValidator headMasterValidator;

    private HeadMasterFacadeImpl headMasterFacade;

    @BeforeEach
    void setUp() {
        headMasterFacade = new HeadMasterFacadeImpl(headMasterService, headMasterMapper, headMasterValidator);
    }

    @Test
    void getAll() {
        List<HeadMaster> headMasters = new ArrayList<>();
        List<HeadMasterDetailsDto> headMasterDetailsDtoList = new ArrayList<>();

        when(headMasterService.get()).thenReturn(headMasters);
        when(headMasterMapper.mapList(headMasters)).thenReturn(headMasterDetailsDtoList);

        List<HeadMasterDetailsDto> result = headMasterFacade.getAll();

        assertEquals(headMasterDetailsDtoList, result);

        verify(headMasterService).get();
        verify(headMasterMapper).mapList(headMasters);
    }

    @Test
    void create_success() {
        CreateHeadMasterRequestDto requestDto = new CreateHeadMasterRequestDto();
        HeadMaster headMaster = new HeadMaster();
        HeadMasterDetailsDto headMasterDetailsDto = new HeadMasterDetailsDto();

        CreateHeadMasterParams params = new CreateHeadMasterParams(1L, 1L);

        when(headMasterValidator.validateCreate(requestDto)).thenReturn(Optional.empty());
        when(headMasterMapper.fromRequestDtoToParams(requestDto)).thenReturn(params);
        when(headMasterService.add(params)).thenReturn(headMaster);
        when(headMasterMapper.map(headMaster)).thenReturn(headMasterDetailsDto);

        HeadMasterDetailsDto result = headMasterFacade.create(requestDto);

        assertEquals(headMasterDetailsDto, result);

        verify(headMasterValidator).validateCreate(requestDto);
        verify(headMasterService).add(params);
        verify(headMasterMapper).map(headMaster);
    }

    @Test
    void create_MissingTeacherId() {
        CreateHeadMasterRequestDto requestDto = new CreateHeadMasterRequestDto();
        when(headMasterValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_TEACHER_ID));

        HeadMasterDetailsDto result = headMasterFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_TEACHER_ID, result.getErrorType());

        verify(headMasterValidator).validateCreate(requestDto);
    }

    @Test
    void create_TeacherNotFound() {
        CreateHeadMasterRequestDto requestDto = new CreateHeadMasterRequestDto();
        when(headMasterValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.TEACHER_NOT_FOUND));

        HeadMasterDetailsDto result = headMasterFacade.create(requestDto);

        assertEquals(ErrorType.TEACHER_NOT_FOUND, result.getErrorType());

        verify(headMasterValidator).validateCreate(requestDto);
    }

    @Test
    void create_MissingClassId() {
        CreateHeadMasterRequestDto requestDto = new CreateHeadMasterRequestDto();
        when(headMasterValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_SCHOOL_CLASS_ID));

        HeadMasterDetailsDto result = headMasterFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_SCHOOL_CLASS_ID, result.getErrorType());

        verify(headMasterValidator).validateCreate(requestDto);
    }

    @Test
    void create_ClassNotFound() {
        CreateHeadMasterRequestDto requestDto = new CreateHeadMasterRequestDto();
        when(headMasterValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.CLASS_NOT_FOUND));

        HeadMasterDetailsDto result = headMasterFacade.create(requestDto);

        assertEquals(ErrorType.CLASS_NOT_FOUND, result.getErrorType());

        verify(headMasterValidator).validateCreate(requestDto);
    }

    @Test
    void findByTeacherId_success() {
        HeadMaster headMaster = new HeadMaster();
        HeadMasterDetailsDto headMasterDetailsDto = new HeadMasterDetailsDto();

        when(headMasterService.findByTeacherId(1L)).thenReturn(headMaster);
        when(headMasterMapper.map(headMaster)).thenReturn(headMasterDetailsDto);

        HeadMasterDetailsDto result = headMasterFacade.getByTeacherId(1L);

        assertEquals(headMasterDetailsDto, result);

        verify(headMasterService).findByTeacherId(1L);
        verify(headMasterMapper).map(headMaster);
    }

    @Test
    void findByTeacherId_HeadMasterNotFound() {
        when(headMasterService.findByTeacherId(1L)).thenReturn(null);

        HeadMasterDetailsDto headMasterDetailsDto = headMasterFacade.getByTeacherId(1L);

        assertEquals(ErrorType.HEAD_MASTER_NOT_FOUND, headMasterDetailsDto.getErrorType());

        verify(headMasterService).findByTeacherId(1L);
    }
}
