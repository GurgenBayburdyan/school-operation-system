package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.mapper.PupilMapper;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.params.CreatePupilParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/pupils")
public class PupilController {
    private final PupilService pupilService;
    private final PupilMapper pupilMapper;

    private static final Logger logger = LoggerFactory.getLogger(PupilController.class);

    @GetMapping
    public ResponseEntity<List<PupilDetailsDto>> getPupils() {
        logger.info("Executing get all pupils rest API");


        List<Pupil> response = pupilService.getPupils();
        logger.info("Successfully executed get all pupils rest API, response entity - {}", response);
        return ResponseEntity.ok(pupilMapper.mapList(response));
    }

    @PostMapping
    public ResponseEntity<PupilDetailsDto> addPupil(@RequestBody CreatePupilRequestDto requestDto) {
        logger.info("Executing add pupil for the provided request to - {}:", requestDto);

        CreatePupilParams params = new CreatePupilParams();
        params.setFirstName(requestDto.getFirstName());
        params.setLastName(requestDto.getLastName());
        params.setDateOfBirth(requestDto.getDateOfBirth());
        Pupil response = pupilService.addPupil(params);

        logger.info("Successfully executed add pupil rest API, response entity - {}", response);
        return ResponseEntity.ok(pupilMapper.map(response));
    }
}