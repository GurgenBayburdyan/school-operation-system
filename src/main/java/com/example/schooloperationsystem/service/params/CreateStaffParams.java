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
    private final Long schoolId;

    public CreateStaffParams(String firstName, String lastName, LocalDateTime dateOfBirth, Long schoolId) {
        Assert.notNull(firstName, "the first name should not be null");
        this.firstName = firstName;
        Assert.notNull(firstName, "the last name should not be null");
        this.lastName = lastName;
        Assert.notNull(firstName, "the date of birth should not be null");
        this.dateOfBirth = dateOfBirth;
        Assert.notNull(firstName, "the school id should not be null");
        this.schoolId = schoolId;
    }
}
