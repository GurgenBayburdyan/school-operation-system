package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

@Setter
@Getter
@ToString
public class CreatePupilInClassParams {
    private final Long schoolClassId;
    private final Long pupilId;

    public CreatePupilInClassParams(Long schoolClassId, Long pupilId) {
        Assert.notNull(schoolClassId, "the school class id should not be null");
        this.schoolClassId = schoolClassId;
        Assert.notNull(pupilId, "the pupil id should not be null");
        this.pupilId = pupilId;
    }
}
