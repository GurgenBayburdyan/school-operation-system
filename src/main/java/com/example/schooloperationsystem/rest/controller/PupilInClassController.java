package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilInClassDetailsDto;
import com.example.schooloperationsystem.rest.facade.PupilInClassFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/pupilInClass")
public class PupilInClassController {

    private final PupilInClassFacade pupilInClassFacade;

    @GetMapping
    public ResponseEntity<List<PupilInClassDetailsDto>> getAll() {
        log.info("Executing get all pupils in classes rest API");

        ResponseEntity<List<PupilInClassDetailsDto>> responseEntity = ResponseEntity.ok(pupilInClassFacade.getAll());

        log.info("Successfully executed get all pupils in classes, response entity-{}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<PupilInClassDetailsDto> create(@RequestBody CreatePupilInClassRequestDto requestDto) {
        log.info("Executing create pupil in classes rest API, request-{}", requestDto);

        ResponseEntity<PupilInClassDetailsDto> responseEntity = ResponseEntity.ok(pupilInClassFacade.create(requestDto));

        log.info("Successfully executed create pupil in class, response entity-{}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/classes/{classId}")
    public ResponseEntity<List<PupilInClassDetailsDto>> getByClassId(@PathVariable("classId") Long classId) {
        log.info("Executing get pupils in class by class id rest API, id-{}", classId);

        ResponseEntity<List<PupilInClassDetailsDto>> responseEntity = ResponseEntity.ok(pupilInClassFacade.getByClassId(classId));

        log.info("Successfully executed get pupils in class by class id, response entity-{}", responseEntity);
        return responseEntity;
    }

    @DeleteMapping("/{pupilId}")
    public ResponseEntity<PupilInClassDetailsDto> deleteByPupilId(@PathVariable("pupilId") Long pupilId) {
        log.info("Executing delete pupil in class by pupil id rest API, id-{}", pupilId);

        ResponseEntity<PupilInClassDetailsDto> responseEntity = ResponseEntity.ok(pupilInClassFacade.deleteByPupilId(pupilId));

        log.info("Successfully executed delete pupil in class by pupil id, response entity-{}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/{pupilId}")
    public ResponseEntity<PupilInClassDetailsDto> getByPupilId(@PathVariable("pupilId") Long pupilId) {
        log.info("Executing get pupil in class by pupil id rest API, id-{}", pupilId);

        ResponseEntity<PupilInClassDetailsDto> responseEntity = ResponseEntity.ok(pupilInClassFacade.getByPupilId(pupilId));

        log.info("Successfully executed get pupils in class by pupil id, response entity-{}", responseEntity);
        return responseEntity;
    }
}
