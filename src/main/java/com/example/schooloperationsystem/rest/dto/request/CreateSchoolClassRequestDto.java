package com.example.schooloperationsystem.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gurgen Bayburdyan
 */
@Setter
@Getter
public class CreateSchoolClassRequestDto {
    @JsonProperty("grade")
    private int grade;

    @JsonProperty("classLetter")
    private char classLetter;

}
