package com.example.schooloperationsystem.rest.facade;

import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import com.example.schooloperationsystem.rest.dto.response.PupilInClassDetailsDto;

import java.util.List;

public interface PupilInClassFacade {
    List<PupilInClassDetailsDto> getPupilsInClass();

    PupilInClassDetailsDto addPupilInClass(CreatePupilInClassRequestDto requestDto);

    List<PupilInClassDetailsDto> getPupilsByClassId(Long classId);

    PupilInClassDetailsDto deletePupilByPupilId(Long pupilId);

    PupilInClassDetailsDto getPupilInClassByPupilId(Long pupilId);
}
