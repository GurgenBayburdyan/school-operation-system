package com.example.schooloperationsystem.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class PupilInClassDetailsDto {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("dateOfBirth")
    private LocalDateTime dateOfBirth;

    @JsonProperty("classId")
    private Long classId;

    @JsonProperty("pupilId")
    private Long pupilId;

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

    public void setPupilId(Long pupilId) {
        if (pupilId == null) {
            this.errorType = ErrorType.MISSING_PUPIL_ID;
        }
        this.pupilId = pupilId;
    }


    private enum ErrorType {
        MISSING_FIRST_NAME,
        MISSING_LAST_NAME,
        MISSING_DATE_OF_BIRTH,
        MISSING_PUPIL_ID,
        MISSING_CLASS_ID
    }
}