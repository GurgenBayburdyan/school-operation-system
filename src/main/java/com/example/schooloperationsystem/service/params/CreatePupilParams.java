package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreatePupilParams {
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;

    public CreatePupilParams(String firstName, String lastName, LocalDateTime dateOfBirth) {
        if (firstName == null || lastName == null || dateOfBirth == null) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
}