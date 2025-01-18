package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.rest.dto.StaffDto;
import com.example.schooloperationsystem.rest.dto.TeacherDto;
import com.example.schooloperationsystem.rest.dto.response.TeacherDetailsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class TeacherMapper {

    private final StaffMapper staffMapper;

    public TeacherDetailsDto mapToTeacherDetailsDto(Teacher teacher) {
        log.trace("Mapping teacher - {} to teacher details dto", teacher);

        Staff staff = teacher.getStaff();
        TeacherDetailsDto detailsDto = new TeacherDetailsDto();
        detailsDto.setFirstName(staff.getFirstName());
        detailsDto.setLastName(staff.getLastName());
        detailsDto.setDateOfBirth(staff.getDateOfBirth());
        detailsDto.setStaffId(staff.getId());

        log.trace("Mapped teacher details dto {}", detailsDto);
        return detailsDto;
    }

    public List<TeacherDetailsDto> mapList(List<Teacher> teachers) {
        List<TeacherDetailsDto> detailsDtos = new ArrayList<>();
        for (Teacher teacher : teachers) {
            detailsDtos.add(mapToTeacherDetailsDto(teacher));
        }
        return detailsDtos;
    }

    public TeacherDto mapToTeacherDto(Teacher teacher) {
        log.trace("Mapping teacher - {} to teacher dto", teacher);
        TeacherDto teacherDto = new TeacherDto();
        Staff staff = teacher.getStaff();

        StaffDto staffDto = staffMapper.mapToStaffDto(staff);

        teacherDto.setStaffDto(staffDto);
        log.trace("Mapped teacher dto {}", teacherDto);
        return teacherDto;
    }
}