package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CreateStaffParams {
    private final String firstName;
    private final String lastName;
    private final LocalDateTime dateOfBirth;

    public CreateStaffParams(String firstName, String lastName, LocalDateTime dateOfBirth) {
        Assert.notNull(firstName, "the first name should not be null");
        this.firstName = firstName;
        Assert.notNull(firstName, "the last name should not be null");
        this.lastName = lastName;
        Assert.notNull(firstName, "the date of birth should not be null");
        this.dateOfBirth = dateOfBirth;
    }
}
