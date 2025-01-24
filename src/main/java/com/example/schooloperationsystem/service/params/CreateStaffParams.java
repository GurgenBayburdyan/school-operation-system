package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
    //todo please implement toString
public class CreateStaffParams {
    //todo lets make final
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;

    public CreateStaffParams(String firstName, String lastName, LocalDateTime dateOfBirth) {
        //todo please assert all fields one by one
        if (firstName == null || lastName == null || dateOfBirth == null) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
}
