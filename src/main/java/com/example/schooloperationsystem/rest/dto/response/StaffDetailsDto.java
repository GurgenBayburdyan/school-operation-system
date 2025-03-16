package com.example.schooloperationsystem.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StaffDetailsDto  extends AbstractErrorAwareDetailsDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("dateOfBirth")
    private LocalDateTime dateOfBirth;

    @JsonProperty("schoolId")
    private Long schoolId;

    public StaffDetailsDto(ErrorType errorType) {
        super(errorType);
    }

    public StaffDetailsDto() {
        super(null);
    }
}
