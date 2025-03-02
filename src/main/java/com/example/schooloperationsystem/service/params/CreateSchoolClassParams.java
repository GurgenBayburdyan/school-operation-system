package com.example.schooloperationsystem.service.params;

import com.example.schooloperationsystem.entity.School;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
    private final Long schoolId;

    public CreateSchoolClassParams(Character classLetter, Integer grade, Long schoolId) {
        Assert.notNull(grade, "the grade should not be null");
        this.grade = grade;
        Assert.notNull(classLetter, "the class letter should not be null");
        this.classLetter = classLetter;
        Assert.notNull(schoolId, "the school id should not be null");
        this.schoolId = schoolId;
    }
}
