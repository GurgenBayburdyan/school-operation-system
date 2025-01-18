package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateTeacherParams {
    private Long staffId;

    public CreateTeacherParams(Long staffId) {
        if (staffId == null) {
            throw new IllegalArgumentException();
        }
        this.staffId = staffId;
    }
}