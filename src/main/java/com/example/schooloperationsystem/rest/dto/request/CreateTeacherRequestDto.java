package com.example.schooloperationsystem.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gurgen Bayburdyan
 */
@Getter
@Setter
public class CreateTeacherRequestDto {
    @JsonProperty("staffId")
    private Long staffId;
}
