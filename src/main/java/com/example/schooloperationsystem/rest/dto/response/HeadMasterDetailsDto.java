package com.example.schooloperationsystem.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class HeadMasterDetailsDto {
    @JsonProperty("classId")
    private Long classId;

    @JsonProperty("teacherId")
    private Long teacherId;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("dateOfBirth")
    private LocalDateTime dateOfBirth;

    @JsonProperty("errorType")
    private ErrorType errorType;


    public void setFirstName(String firstName) {
        if (firstName == null) {
            this.errorType = ErrorType.MISSING_FIRST_NAME;
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) {
            this.errorType = ErrorType.MISSING_LAST_NAME;
        }
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        if (dateOfBirth == null) {
            this.errorType = ErrorType.MISSING_DATE_OF_BIRTH;
        }
        this.dateOfBirth = dateOfBirth;
    }

    public void setClassId(Long classId) {
        if (classId == null) {
            this.errorType = ErrorType.MISSING_CLASS_ID;
        }
        this.classId = classId;
    }

    public void setTeacherId(Long teacherId) {
        if (teacherId == null) {
            this.errorType = ErrorType.MISSING_PUPIL_ID;
        }
        this.teacherId = teacherId;
    }


    private enum ErrorType {
        MISSING_FIRST_NAME,
        MISSING_LAST_NAME,
        MISSING_DATE_OF_BIRTH,
        MISSING_PUPIL_ID,
        MISSING_CLASS_ID
    }
}
