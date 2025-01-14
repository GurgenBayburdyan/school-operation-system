package com.example.schooloperationsystem.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gurgen Bayburdyan
 */
@Setter
@Getter
public class SchoolClassDetailsDto {
    @JsonProperty("grade")
    private int grade;

    @JsonProperty("classLetter")
    private char classLetter;

}
