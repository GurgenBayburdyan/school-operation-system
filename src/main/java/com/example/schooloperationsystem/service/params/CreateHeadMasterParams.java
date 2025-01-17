package com.example.schooloperationsystem.service.params;

import com.example.schooloperationsystem.rest.dto.SchoolClassDto;
import com.example.schooloperationsystem.rest.dto.TeacherDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateHeadMasterParams {
    private TeacherDto teacherDto;
    private SchoolClassDto schoolClassDto;

    public CreateHeadMasterParams(TeacherDto teacherDto, SchoolClassDto schoolClassDto) {
        if (teacherDto == null || schoolClassDto == null) {
            throw new IllegalArgumentException();
        }
        this.teacherDto = teacherDto;
        this.schoolClassDto = schoolClassDto;
    }
}
