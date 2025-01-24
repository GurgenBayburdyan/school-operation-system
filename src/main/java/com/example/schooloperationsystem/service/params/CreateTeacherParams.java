package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateTeacherParams {
    private Long staffId;

    public CreateTeacherParams(Long staffId) {
        //todo Assert.notNull(staffId, "the staff id should not be null);
        if (staffId == null) {
            throw new IllegalArgumentException();
        }
        this.staffId = staffId;
    }
}
