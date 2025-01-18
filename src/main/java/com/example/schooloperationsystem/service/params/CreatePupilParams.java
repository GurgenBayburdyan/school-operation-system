package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePupilParams {
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    public CreatePupilParams(String firstName, String lastName, String dateOfBirth) {
        if (firstName == null || lastName == null || dateOfBirth == null) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
}