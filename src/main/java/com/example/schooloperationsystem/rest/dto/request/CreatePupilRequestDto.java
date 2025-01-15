package com.example.schooloperationsystem.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gurgen Bayburdyan
 */
@Setter
@Getter
public class CreatePupilRequestDto {
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

}