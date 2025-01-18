package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSchoolClassParams {
    private Integer grade;
    private Character classLetter;

    public CreateSchoolClassParams(Character classLetter, Integer grade) {
        if (grade == null || classLetter == null) {
            throw new IllegalArgumentException();
        }
        this.grade = grade;
        this.classLetter = classLetter;
    }
}
