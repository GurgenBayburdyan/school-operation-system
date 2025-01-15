package com.example.schooloperationsystem.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Gurgen Bayburdyan
 */
@Getter
@Setter
public class StaffDto {
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
}
