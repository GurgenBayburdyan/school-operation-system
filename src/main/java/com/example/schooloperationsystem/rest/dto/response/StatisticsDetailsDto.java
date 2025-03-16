package com.example.schooloperationsystem.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Map;

@Getter
@Setter
@ToString
public class StatisticsDetailsDto {

    @JsonProperty("pupilCount")
    private Integer pupilCount;

    @JsonProperty("classCount")
    private Integer classCount;

    @JsonProperty("staffCount")
    private Integer staffCount;

    @JsonProperty("pupilsInClasses")
    private Map<Long, Integer> pupilsInClasses;

    @JsonProperty("maxCountOfPupilsInClass")
    private Integer maxCountOfPupilsInClass;

    @JsonProperty("minCountOfPupilsInClass")
    private Integer minCountOfPupilsInClass;

    @JsonProperty("schoolCount")
    private Integer schoolCount;

}
