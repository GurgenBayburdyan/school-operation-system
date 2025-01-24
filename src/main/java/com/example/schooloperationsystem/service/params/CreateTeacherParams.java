package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

@Setter
@Getter
@ToString
public class CreateTeacherParams {
    private final Long staffId;

    public CreateTeacherParams(Long staffId) {
        Assert.notNull(staffId, "the staff id should not be null");
        this.staffId = staffId;
    }
}
