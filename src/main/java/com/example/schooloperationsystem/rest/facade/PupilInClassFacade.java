package com.example.schooloperationsystem.rest.facade;

import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilInClassDetailsDto;
import java.util.List;

public interface PupilInClassFacade {
    List<PupilInClassDetailsDto> getAll();

    PupilInClassDetailsDto create(CreatePupilInClassRequestDto requestDto);

    List<PupilInClassDetailsDto> getByClassId(Long classId);

    PupilInClassDetailsDto deleteByPupilId(Long pupilId);

    PupilInClassDetailsDto getByPupilId(Long pupilId);
}
