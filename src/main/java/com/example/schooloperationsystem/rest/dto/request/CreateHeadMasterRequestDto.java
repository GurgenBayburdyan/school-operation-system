package com.example.schooloperationsystem.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateHeadMasterRequestDto {
    @JsonProperty("teacherId")
    private Long teacherId;

    @JsonProperty("classId")
    private Long classId;

}
