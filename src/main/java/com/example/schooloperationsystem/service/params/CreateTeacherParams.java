package com.example.schooloperationsystem.service.params;

import com.example.schooloperationsystem.rest.dto.StaffDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gurgen Bayburdyan
 */

@Setter
@Getter
public class CreateTeacherParams {
    private StaffDto staffDto;
}