package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.mapper.PupilInClassMapper;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilInClassDetailsDto;
import com.example.schooloperationsystem.service.PupilInClassService;
import com.example.schooloperationsystem.service.params.CreatePupilInClassParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/pupilInClass")
public class PupilInClassController {

    private final PupilInClassService pupilInClassService;
    private final PupilInClassMapper pupilInClassMapper;

    private static final Logger logger = LoggerFactory.getLogger(PupilInClassController.class);

    @GetMapping
    public ResponseEntity<List<PupilInClassDetailsDto>> getPupilsInClass() {
        logger.info("Executing get all pupils in classes rest API");

        List<PupilInClass> response = pupilInClassService.getPupilInClasses();

        logger.info("Successfully executed get pupils in class rest API, response entity - {}", response);
        return ResponseEntity.ok(pupilInClassMapper.mapList(response));
    }

    @PostMapping
    public ResponseEntity<PupilInClassDetailsDto> addPupilInClass(@RequestBody CreatePupilInClassRequestDto requestDto) {
        logger.info("Executing add pupil in class for the provided request to-{}", requestDto);

        CreatePupilInClassParams params = new CreatePupilInClassParams();

        params.setPupilId(requestDto.getPupilId());
        params.setSchoolClassId(requestDto.getSchoolClassId());
        PupilInClass response = pupilInClassService.addPupil(params);

        logger.info("Successfully executed add pupil in class rest API, response entity - {}", response);
        return ResponseEntity.ok(pupilInClassMapper.map(response));
    }

    @GetMapping("/{classId}")
    public ResponseEntity<List<PupilInClassDetailsDto>> getPupilsByClassId(@PathVariable("classId") Long classId) {
        logger.info("Executing get pupil in class by class id-{}", classId);

        List<PupilInClass> response = pupilInClassService.getPupilsBySchoolClassId(classId);

        logger.info("Successfully executed get pupil in class by class id rest API, response entity - {}", response);
        return ResponseEntity.of(Optional.ofNullable(pupilInClassMapper.mapList(response)));
    }

    @DeleteMapping("/{pupilId}")
    public ResponseEntity<PupilInClassDetailsDto> deletePupilById(@PathVariable("pupilId") Long pupilId) {
        logger.info("Executing delete pupil in class by id-{}", pupilId);

        PupilInClass response = pupilInClassService.deletePupilById(pupilId);

        logger.info("Successfully executed delete pupil in class by id rest API, response entity - {}", response);
        return ResponseEntity.ok(pupilInClassMapper.map(response));
    }
}
