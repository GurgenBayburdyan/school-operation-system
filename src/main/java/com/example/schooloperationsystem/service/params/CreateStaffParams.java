package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
public class CreateStaffParams {
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;

}