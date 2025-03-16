package com.example.schooloperationsystem.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class HeadMasterDetailsDto extends AbstractErrorAwareDetailsDto {
    @JsonProperty("classId")
    private Long classId;

    @JsonProperty("teacherId")
    private Long teacherId;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("dateOfBirth")
    private LocalDateTime dateOfBirth;

    public HeadMasterDetailsDto(ErrorType errorType) {
        super(errorType);
    }

    public HeadMasterDetailsDto() {
        super(null);
    }

}
