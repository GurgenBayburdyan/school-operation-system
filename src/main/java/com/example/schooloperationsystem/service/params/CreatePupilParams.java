package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePupilParams {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}