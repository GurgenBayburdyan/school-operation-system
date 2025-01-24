package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

@Setter
@Getter
@ToString
public class CreateHeadMasterParams {
    private final Long teacherId;
    private final Long schoolClassId;

    public CreateHeadMasterParams(Long teacherId, Long schoolClassId) {
        Assert.notNull(teacherId, "the teacher id should not be null");
        this.teacherId = teacherId;
        Assert.notNull(teacherId, "the school class id should not be null");
        this.schoolClassId = schoolClassId;
    }
}
