package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePupilInClassParams {
    private Long schoolClassId;
    private Long pupilId;
}
