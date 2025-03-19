package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.HeadMasterDetailsDto;
import com.example.schooloperationsystem.service.params.CreateHeadMasterParams;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class HeadMasterMapper {

    private static final Logger logger = LoggerFactory.getLogger(HeadMasterMapper.class);

    public HeadMasterDetailsDto map(HeadMaster headMaster) {
        logger.trace("Mapping headMaster - {} to headMaster details dto", headMaster);

        Teacher teacher = headMaster.getTeacher();
        Staff staff = teacher.getStaff();
        HeadMasterDetailsDto detailsDto = new HeadMasterDetailsDto();
        detailsDto.setClassId(headMaster.getSchoolClass().getId());
        detailsDto.setTeacherId(teacher.getId());
        detailsDto.setFirstName(staff.getFirstName());
        detailsDto.setLastName(staff.getLastName());
        detailsDto.setDateOfBirth(staff.getDateOfBirth());

        logger.trace("Mapped headMaster {}", detailsDto);
        return detailsDto;
    }

    public List<HeadMasterDetailsDto> mapList(List<HeadMaster> headMasters) {
        List<HeadMasterDetailsDto> detailsDtos = new ArrayList<>();
        for (HeadMaster headMaster : headMasters) {
            detailsDtos.add(map(headMaster));
        }
        return detailsDtos;
    }

    public CreateHeadMasterParams fromRequestDtoToParams(CreateHeadMasterRequestDto requestDto){
        return new CreateHeadMasterParams(requestDto.getTeacherId(), requestDto.getClassId());
    }
}