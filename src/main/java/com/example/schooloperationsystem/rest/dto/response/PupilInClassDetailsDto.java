package com.example.schooloperationsystem.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gurgen Bayburdyan
 */
@Setter
@Getter
public class PupilInClassDetailsDto {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

    @JsonProperty("classId")
    private Long classId;

    @JsonProperty("pupilId")
    private Long pupilId;

}