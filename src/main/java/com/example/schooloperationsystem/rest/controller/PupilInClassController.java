package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.mapper.PupilInClassMapper;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilInClassDetailsDto;
import com.example.schooloperationsystem.service.PupilInClassService;
import com.example.schooloperationsystem.service.params.CreatePupilInClassParams;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pupilInClass")
public class PupilInClassController {
    private final PupilInClassService pupilInClassService;
    private final PupilInClassMapper pupilInClassMapper;

    public PupilInClassController(PupilInClassService pupilInClassService, PupilInClassMapper pupilInClassMapper) {
        this.pupilInClassService = pupilInClassService;
        this.pupilInClassMapper = pupilInClassMapper;
    }

    @GetMapping
    public List<PupilInClassDetailsDto> getPupilsInClasses() {
        List<PupilInClass> response = pupilInClassService.getPupilInClasses();
        return pupilInClassMapper.mapList(response);
    }

    @PostMapping
    public PupilInClassDetailsDto addPupilInClass(@RequestBody CreatePupilInClassRequestDto requestDto) {
        CreatePupilInClassParams params = new CreatePupilInClassParams();
        params.setPupil(requestDto.getPupil());
        params.setSchoolClass(requestDto.getSchoolClass());
        PupilInClass response = pupilInClassService.addPupil(params);
        return pupilInClassMapper.map(response);
    }
}
