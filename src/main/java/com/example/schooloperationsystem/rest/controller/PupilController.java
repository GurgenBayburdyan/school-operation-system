package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import com.example.schooloperationsystem.rest.facade.PupilFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/pupils")
public class PupilController {

    private final PupilFacade pupilFacade;

    @GetMapping
    public ResponseEntity<List<PupilDetailsDto>> getAll() {
        log.info("Executing get all pupils rest API");

        ResponseEntity<List<PupilDetailsDto>> responseEntity = ResponseEntity.ok(pupilFacade.getAll());

        log.info("Successfully executed get all pupils, response entity-{}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<PupilDetailsDto> create(@RequestBody CreatePupilRequestDto requestDto) {
        log.info("Executing create pupil rest API, request-{}", requestDto);

        ResponseEntity<PupilDetailsDto> responseEntity = ResponseEntity.ok(pupilFacade.create(requestDto));

        log.info("Successfully executed create pupil, response entity-{}", responseEntity);
        return responseEntity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PupilDetailsDto> deleteById(@PathVariable("id") Long id) {
        log.info("Executing delete pupil by id rest API, id-{}", id);

        ResponseEntity<PupilDetailsDto> responseEntity = ResponseEntity.ok(pupilFacade.deleteById(id));

        log.info("Successfully executed delete pupil, response entity-{}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<List<PupilDetailsDto>> getBySchoolId(@PathVariable("schoolId") Long schoolId) {
        log.info("Executing get pupils by school id rest API, id-{}", schoolId);

        ResponseEntity<List<PupilDetailsDto>> responseEntity = ResponseEntity.ok(pupilFacade.getBySchoolId(schoolId));

        log.info("Successfully executed get pupils by school id, response entity-{}", responseEntity);
        return responseEntity;
    }
}
