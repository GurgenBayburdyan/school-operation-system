package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.mapper.PupilMapper;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.params.CreatePupilParams;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Gurgen Bayburdyan
 */
@RestController
@RequestMapping("/pupils")
public class PupilController {
    private final PupilService pupilService;
    private final PupilMapper pupilMapper;

    public PupilController(PupilService pupilService, PupilMapper pupilMapper) {
        this.pupilService = pupilService;
        this.pupilMapper = pupilMapper;
    }

    @GetMapping
    public List<PupilDetailsDto> getPupils() {
        List<Pupil> response = pupilService.getPupils();
        return pupilMapper.mapList(response);
    }

    @PostMapping
    public PupilDetailsDto addPupil(@RequestBody CreatePupilRequestDto requestDto) {
        CreatePupilParams params = new CreatePupilParams();
        params.setFirstName(requestDto.getFirstName());
        params.setLastName(requestDto.getLastName());
        params.setDateOfBirth(requestDto.getDateOfBirth());
        Pupil response = pupilService.addPupil(params);
        return pupilMapper.map(response);
    }
}