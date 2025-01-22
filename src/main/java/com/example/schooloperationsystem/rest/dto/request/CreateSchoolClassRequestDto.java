package com.example.schooloperationsystem.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CreateSchoolClassRequestDto {
    @JsonProperty("grade")
    private Integer grade;

    @JsonProperty("classLetter")
    private Character classLetter;
}
