package com.example.schooloperationsystem.rest.dto;

/**
 * @author Gurgen Bayburdyan
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PupilDto {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}
