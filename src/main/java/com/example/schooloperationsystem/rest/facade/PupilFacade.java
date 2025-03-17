package com.example.schooloperationsystem.rest.facade;

import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import java.util.List;

public interface PupilFacade {

    List<PupilDetailsDto> getAll();

    PupilDetailsDto create(CreatePupilRequestDto requestDto);

    PupilDetailsDto deleteById(Long id);

    List<PupilDetailsDto> getBySchoolId(Long schoolId);

}
