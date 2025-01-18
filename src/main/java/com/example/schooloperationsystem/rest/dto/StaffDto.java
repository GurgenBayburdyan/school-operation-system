package com.example.schooloperationsystem.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class StaffDto {
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
}
