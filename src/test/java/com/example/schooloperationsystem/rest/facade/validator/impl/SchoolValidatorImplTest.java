package com.example.schooloperationsystem.rest.facade.validator.impl;

import com.example.schooloperationsystem.rest.dto.request.CreateSchoolRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.facade.validator.SchoolValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SchoolValidatorImplTest {

    private SchoolValidator schoolValidator;

    @BeforeEach
    void setUp() {
        schoolValidator = new SchoolValidatorImpl();
    }

    @Test
    void validateCreate_MissingAddress() {
        CreateSchoolRequestDto requestDto = new CreateSchoolRequestDto();
        requestDto.setAddress(null);
        requestDto.setNamedAfter("namedAfter");
        requestDto.setNumber(1234);
        requestDto.setPhotoUrl("photoUrl");
        Optional<ErrorType> errorType = schoolValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_ADDRESS);
    }

    @Test
    void validateCreate_MissingNamedAfter() {
        CreateSchoolRequestDto requestDto = new CreateSchoolRequestDto();
        requestDto.setAddress("address");
        requestDto.setNamedAfter(null);
        requestDto.setNumber(1234);
        requestDto.setPhotoUrl("photoUrl");
        Optional<ErrorType> errorType = schoolValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_NAMED_AFTER);
    }

    @Test
    void validateCreate_MissingNumber() {
        CreateSchoolRequestDto requestDto = new CreateSchoolRequestDto();
        requestDto.setAddress("address");
        requestDto.setNamedAfter("namedAfter");
        requestDto.setNumber(null);
        requestDto.setPhotoUrl("photoUrl");
        Optional<ErrorType> errorType = schoolValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_NUMBER);
    }

    @Test
    void validateCreate_MissingPhotoUrl() {
        CreateSchoolRequestDto requestDto = new CreateSchoolRequestDto();
        requestDto.setAddress("address");
        requestDto.setNamedAfter("namedAfter");
        requestDto.setNumber(1234);
        requestDto.setPhotoUrl(null);
        Optional<ErrorType> errorType = schoolValidator.validateCreate(requestDto);
        assertThat(errorType).contains(ErrorType.MISSING_PHOTO_URL);
    }
}