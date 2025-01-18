package com.example.schooloperationsystem.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SchoolClassDetailsDto {
    @JsonProperty("grade")
    private Integer grade;

    @JsonProperty("classLetter")
    private Character classLetter;

    @JsonProperty("errorType")
    private ErrorType errorType;

    public void setGrade(Integer grade) {
        if (grade == null) {
            this.errorType = ErrorType.MISSING_GRADE;
            return;
        }
        this.grade = grade;
    }

    public void setClassLetter(Character classLetter) {
        if (classLetter == null) {
            this.errorType = ErrorType.MISSING_CLASS_LETTER;
            return;
        }
        this.classLetter = classLetter;
    }

    private enum ErrorType {
        MISSING_GRADE,
        MISSING_CLASS_LETTER
    }
}