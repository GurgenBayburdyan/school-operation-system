package com.example.schooloperationsystem.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class CreatePupilRequestDto {
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("dateOfBirth")
    private LocalDateTime dateOfBirth;

    @JsonProperty("schoolId")
    private Long schoolId;

}