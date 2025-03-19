package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.mapper.StaffMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import com.example.schooloperationsystem.rest.facade.StaffFacade;
import com.example.schooloperationsystem.rest.facade.validator.StaffValidator;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.params.CreateStaffParams;
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
class StaffFacadeImplTest {

    @Mock
    private StaffService service;

    @Mock
    private StaffValidator staffValidator;

    @Mock
    private StaffMapper staffMapper;

    private StaffFacade staffFacade;

    @BeforeEach
    void setUp() {
        staffFacade = new StaffFacadeImpl(service, staffValidator, staffMapper);
    }

    @Test
    void getAll() {
        List<Staff> staff = new ArrayList<>();
        List<StaffDetailsDto> staffDetailsDtos = new ArrayList<>();

        when(service.get()).thenReturn(staff);
        when(staffMapper.mapList(staff)).thenReturn(staffDetailsDtos);

        List<StaffDetailsDto> result = staffFacade.getAll();

        assertEquals(staffDetailsDtos, result);

        verify(service).get();
        verify(staffMapper).mapList(staff);
    }

    @Test
    void create_success() {
        CreateStaffRequestDto requestDto = new CreateStaffRequestDto();
        Staff staff = new Staff();
        StaffDetailsDto staffDetailsDto = new StaffDetailsDto();

        CreateStaffParams params = new CreateStaffParams(
                "firstName",
                "lastName",
                LocalDateTime.now(),
                1L
        );

        when(staffValidator.validateCreate(requestDto)).thenReturn(Optional.empty());
        when(staffMapper.fromRequestDtoToParams(requestDto)).thenReturn(params);
        when(service.add(params)).thenReturn(staff);
        when(staffMapper.map(staff)).thenReturn(staffDetailsDto);

        StaffDetailsDto result = staffFacade.create(requestDto);

        assertEquals(staffDetailsDto, result);

        verify(staffValidator).validateCreate(requestDto);
        verify(service).add(params);
        verify(staffMapper).map(staff);
    }

    @Test
    void create_MissingFirstName() {
        CreateStaffRequestDto requestDto = new CreateStaffRequestDto();

        when(staffValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_FIRST_NAME));

        StaffDetailsDto result = staffFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_FIRST_NAME, result.getErrorType());

        verify(staffValidator).validateCreate(requestDto);
    }

    @Test
    void create_MissingLastName() {
        CreateStaffRequestDto requestDto = new CreateStaffRequestDto();

        when(staffValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_LAST_NAME));

        StaffDetailsDto result = staffFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_LAST_NAME, result.getErrorType());

        verify(staffValidator).validateCreate(requestDto);
    }

    @Test
    void create_MissingDateOfBirth() {
        CreateStaffRequestDto requestDto = new CreateStaffRequestDto();

        when(staffValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_DATE_OF_BIRTH));

        StaffDetailsDto result = staffFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_DATE_OF_BIRTH, result.getErrorType());

        verify(staffValidator).validateCreate(requestDto);
    }

    @Test
    void create_MissingSchoolId() {
        CreateStaffRequestDto requestDto = new CreateStaffRequestDto();

        when(staffValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_SCHOOL_ID));

        StaffDetailsDto result = staffFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_SCHOOL_ID, result.getErrorType());

        verify(staffValidator).validateCreate(requestDto);
    }

    @Test
    void create_SchoolNotFound() {
        CreateStaffRequestDto requestDto = new CreateStaffRequestDto();

        when(staffValidator.validateCreate(requestDto)).thenReturn(Optional.of(ErrorType.MISSING_SCHOOL_ID));

        StaffDetailsDto result = staffFacade.create(requestDto);

        assertEquals(ErrorType.MISSING_SCHOOL_ID, result.getErrorType());

        verify(staffValidator).validateCreate(requestDto);
    }

    @Test
    void deleteById_success() {
        Staff staff = new Staff();
        StaffDetailsDto staffDetailsDto = new StaffDetailsDto();

        when(service.deleteById(1L)).thenReturn(staff);
        when(staffMapper.map(staff)).thenReturn(staffDetailsDto);

        StaffDetailsDto result = staffFacade.deleteById(1L);

        assertEquals(staffDetailsDto, result);

        verify(service).deleteById(1L);
        verify(staffMapper).map(staff);
    }

    @Test
    void deleteById_StaffNotFound() {
        when(service.deleteById(1L)).thenReturn(null);

        StaffDetailsDto result = staffFacade.deleteById(1L);

        assertEquals(ErrorType.STAFF_NOT_FOUND, result.getErrorType());

        verify(service).deleteById(1L);
    }

    @Test
    void findBySchoolId() {
        List<Staff> staff = new ArrayList<>();
        List<StaffDetailsDto> staffDetailsDtos = new ArrayList<>();

        when(service.findBySchoolId(1L)).thenReturn(staff);
        when(staffMapper.mapList(staff)).thenReturn(staffDetailsDtos);

        List<StaffDetailsDto> result = staffFacade.getBySchoolId(1L);

        assertEquals(staffDetailsDtos, result);

        verify(service).findBySchoolId(1L);
        verify(staffMapper).mapList(staff);
    }
}