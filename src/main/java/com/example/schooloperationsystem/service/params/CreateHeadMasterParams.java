package com.example.schooloperationsystem.service.params;

import com.example.schooloperationsystem.rest.dto.TeacherDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gurgen Bayburdyan
 */
@Setter
@Getter
public class CreateHeadMasterParams {
    private TeacherDto teacherDto;
    private SchoolClassDto schoolClassDto;

}
