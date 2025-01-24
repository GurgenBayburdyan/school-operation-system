package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

@Getter
@Setter
@ToString
public class CreateSchoolClassParams {
    private final Integer grade;
    private final Character classLetter;

    public CreateSchoolClassParams(Character classLetter, Integer grade) {
        Assert.notNull(grade, "the grade should not be null");
        this.grade = grade;
        Assert.notNull(classLetter, "the class letter should not be null");
        this.classLetter = classLetter;
    }
}
