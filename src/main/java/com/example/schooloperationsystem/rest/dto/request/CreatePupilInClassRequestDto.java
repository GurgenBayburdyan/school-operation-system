package com.example.schooloperationsystem.rest.dto.request;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePupilInClassRequestDto {
    @JsonProperty("schoolClass")
    private SchoolClass schoolClass;

    @JsonProperty("pupil")
    private Pupil pupil;
}
