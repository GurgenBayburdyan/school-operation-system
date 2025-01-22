package com.example.schooloperationsystem.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SchoolClassDetailsDto  extends AbstractErrorAwareDetailsDto {
    @JsonProperty("grade")
    private Integer grade;

    @JsonProperty("classLetter")
    private Character classLetter;

}