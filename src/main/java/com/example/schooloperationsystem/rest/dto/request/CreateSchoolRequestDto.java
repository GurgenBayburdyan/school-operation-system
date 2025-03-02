package com.example.schooloperationsystem.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CreateSchoolRequestDto {
    @JsonProperty("number")
    private Integer number;

    @JsonProperty("namedAfter")
    private String namedAfter;

    @JsonProperty("address")
    private String address;

    @JsonProperty("photoUrl")
    private String photoUrl;
}
