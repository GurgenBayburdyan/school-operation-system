package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TeacherMapper {

    private static final Logger logger = LoggerFactory.getLogger(StaffMapper.class);

    public TeacherDetailsDto map(Teacher teacher) {
        log.trace("Mapping teacher - {} to teacher details dto", teacher);
        Staff staff = teacher.getStaff();
        TeacherDetailsDto detailsDto = new TeacherDetailsDto();
        detailsDto.setFirstName(staff.getFirstName());
        detailsDto.setLastName(staff.getLastName());
        detailsDto.setDateOfBirth(staff.getDateOfBirth());
        detailsDto.setStaffId(staff.getId());
        log.trace("Mapped teacher {}", detailsDto);
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
