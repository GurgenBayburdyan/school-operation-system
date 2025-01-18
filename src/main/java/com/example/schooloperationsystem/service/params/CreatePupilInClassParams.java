package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePupilInClassParams {
    private Long schoolClassId;
    private Long pupilId;

    public CreatePupilInClassParams(Long schoolClassId, Long pupilId) {
        if (schoolClassId == null || pupilId == null) {
            throw new IllegalArgumentException();
        }
        this.schoolClassId = schoolClassId;
        this.pupilId = pupilId;
    }
}
