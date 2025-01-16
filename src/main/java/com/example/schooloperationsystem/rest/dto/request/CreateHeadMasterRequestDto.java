package com.example.schooloperationsystem.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gurgen Bayburdyan
 */
@Getter
@Setter
//todo please implement toString using lombok in all request and response dto classes 
public class CreateHeadMasterRequestDto {
    @JsonProperty("teacherId")
    private Long teacherId;

    @JsonProperty("classId")
    private Long classId;
}
