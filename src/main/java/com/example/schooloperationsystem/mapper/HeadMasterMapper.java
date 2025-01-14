package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.rest.dto.response.HeadMasterDetailsDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeadMasterMapper {

    public HeadMasterDetailsDto map(HeadMaster headMaster) {
        Teacher teacher = headMaster.getTeacher();
        Staff staff = teacher.getStaff();

        HeadMasterDetailsDto detailsDto = new HeadMasterDetailsDto();
        detailsDto.setClassId(headMaster.getSchoolClass().getId());
        detailsDto.setTeacherId(teacher.getId());
        detailsDto.setFirstName(staff.getFirstName());
        detailsDto.setLastName(staff.getLastName());
        detailsDto.setDateOfBirth(staff.getDateOfBirth());

        return detailsDto;
    }

    public List<HeadMasterDetailsDto> mapList(List<HeadMaster> headMasters) {
        List<HeadMasterDetailsDto> detailsDtos = new ArrayList<>();
        for (HeadMaster headMaster : headMasters) {
            detailsDtos.add(map(headMaster));
        }
        return detailsDtos;
    }
}
