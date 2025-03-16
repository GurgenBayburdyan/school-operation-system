package com.example.schooloperationsystem.rest.facade;

import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import java.util.List;

public interface PupilFacade {

    List<PupilDetailsDto> getPupils();

    PupilDetailsDto addPupil(CreatePupilRequestDto requestDto);

    PupilDetailsDto deletePupilById(Long id);

    List<PupilDetailsDto> getPupilsBySchoolId(Long schoolId);

}
