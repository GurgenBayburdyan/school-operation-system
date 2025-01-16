package com.example.schooloperationsystem.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CreatePupilInClassRequestDto {
    @JsonProperty("schoolClassId")
    private Long schoolClassId;

    @JsonProperty("pupilId")
    private Long pupilId;
}
