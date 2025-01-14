package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherMapper {
    public TeacherDetailsDto map(Teacher teacher) {
        Staff staff = teacher.getStaff();
        TeacherDetailsDto detailsDto = new TeacherDetailsDto();
        detailsDto.setFirstName(staff.getFirstName());
        detailsDto.setLastName(staff.getLastName());
        detailsDto.setDateOfBirth(staff.getDateOfBirth());
        detailsDto.setStaffId(staff.getId());
        return detailsDto;
    }

    public List<TeacherDetailsDto> mapList(List<Teacher> teachers) {
        List<TeacherDetailsDto> detailsDtos = new ArrayList<>();
        for (Teacher teacher : teachers) {
            detailsDtos.add(map(teacher));
        }
        return detailsDtos;
    }
}
