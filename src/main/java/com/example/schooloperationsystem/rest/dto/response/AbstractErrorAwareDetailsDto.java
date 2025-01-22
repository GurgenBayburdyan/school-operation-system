package com.example.schooloperationsystem.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractErrorAwareDetailsDto {

    @JsonProperty("errorType")
    private ErrorType errorType;

}
