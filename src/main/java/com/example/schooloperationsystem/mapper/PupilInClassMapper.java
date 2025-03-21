package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilInClassDetailsDto;
import com.example.schooloperationsystem.service.params.CreatePupilInClassParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PupilInClassMapper {

    public PupilInClassDetailsDto map(PupilInClass pupilInClass) {
        log.trace("Mapping pupilInClass - {} to pupilInClass details dto", pupilInClass);

        Pupil pupil = pupilInClass.getPupil();
        SchoolClass schoolClass = pupilInClass.getSchoolClass();

        PupilInClassDetailsDto detailsDto = new PupilInClassDetailsDto();
        detailsDto.setFirstName(pupil.getFirstName());
        detailsDto.setLastName(pupil.getLastName());
        detailsDto.setDateOfBirth(pupil.getDateOfBirth());
        detailsDto.setClassId(schoolClass.getId());
        detailsDto.setPupilId(pupil.getId());

        log.trace("Mapped pupilInClass to {}", detailsDto);
        return detailsDto;
    }

    public List<PupilInClassDetailsDto> mapList(List<PupilInClass> pupilsInClasses) {
        List<PupilInClassDetailsDto> detailsDtos = new ArrayList<>();
        for (PupilInClass pupilInClass : pupilsInClasses) {
            detailsDtos.add(map(pupilInClass));
        }
        return detailsDtos;
    }

    public CreatePupilInClassParams fromRequestDtoToParams(CreatePupilInClassRequestDto requestDto) {
        return new CreatePupilInClassParams(requestDto.getSchoolClassId(), requestDto.getPupilId());
    }
}