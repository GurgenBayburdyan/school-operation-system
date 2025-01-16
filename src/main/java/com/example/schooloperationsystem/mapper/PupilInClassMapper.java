package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.rest.dto.response.PupilInClassDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PupilInClassMapper {

    private static final Logger logger = LoggerFactory.getLogger(PupilInClassMapper.class);

    public PupilInClassDetailsDto map(PupilInClass pupilInClass) {
        logger.trace("Mapping pupilInClass - {} to pupilInClass details dto", pupilInClass);

        Pupil pupil = pupilInClass.getPupil();
        SchoolClass schoolClass = pupilInClass.getSchoolClass();
        PupilInClassDetailsDto detailsDto = new PupilInClassDetailsDto();
        detailsDto.setFirstName(pupil.getFirstName());
        detailsDto.setLastName(pupil.getLastName());
        detailsDto.setDateOfBirth(pupil.getDateOfBirth());
        detailsDto.setClassId(schoolClass.getId());
        detailsDto.setPupilId(pupil.getId());

        logger.trace("Mapped pupilInClass to {}", detailsDto);
        return detailsDto;
    }

    public List<PupilInClassDetailsDto> mapList(List<PupilInClass> pupilsInClasses) {
        List<PupilInClassDetailsDto> detailsDtos = new ArrayList<>();
        for (PupilInClass pupilInClass : pupilsInClasses) {
            detailsDtos.add(map(pupilInClass));
        }
        return detailsDtos;
    }
}
