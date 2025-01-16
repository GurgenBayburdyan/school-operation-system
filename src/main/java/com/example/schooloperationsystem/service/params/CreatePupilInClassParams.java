package com.example.schooloperationsystem.service.params;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.rest.dto.PupilDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePupilInClassParams {
    private SchoolClass schoolClass;
    private Pupil pupil;
}
