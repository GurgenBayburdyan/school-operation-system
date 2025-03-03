package com.example.schooloperationsystem.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SchoolDetailsDto extends AbstractErrorAwareDetailsDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("number")
    private Integer number;

    @JsonProperty("namedAfter")
    private String namedAfter;

    @JsonProperty("address")
    private String address;

    @JsonProperty("photoUrl")
    private String photoUrl;
}
