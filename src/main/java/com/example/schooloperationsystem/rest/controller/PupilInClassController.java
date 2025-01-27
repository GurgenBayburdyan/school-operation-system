package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.controller.validator.PupilInClassValidator;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.mapper.PupilInClassMapper;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilInClassDetailsDto;
import com.example.schooloperationsystem.service.PupilInClassService;
import com.example.schooloperationsystem.service.params.CreatePupilInClassParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final PupilInClassValidator pupilInClassValidator;

    @GetMapping
    public ResponseEntity<List<PupilInClassDetailsDto>> getPupilsInClass() {
        log.info("Executing get all pupils in classes rest API");

        List<PupilInClass> response = pupilInClassService.get();
        ResponseEntity<List<PupilInClassDetailsDto>> responseEntity = ResponseEntity.ok(pupilInClassMapper.mapList(response));

        log.info("Successfully executed get pupils in class rest API, response entity - {}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<PupilInClassDetailsDto> addPupilInClass(@RequestBody CreatePupilInClassRequestDto requestDto) {
        log.info("Executing add pupil in class for the provided request to-{}", requestDto);

        Optional<ErrorType> optionalErrorType = pupilInClassValidator.validateCreate(requestDto);

        if (optionalErrorType.isPresent()) {
            PupilInClassDetailsDto pupilInClassDetailsDto = new PupilInClassDetailsDto();
            pupilInClassDetailsDto.setErrorType(optionalErrorType.get());
            log.info("Executing create pupil in class failed, error-{}", optionalErrorType.get());
            return ResponseEntity.ok(pupilInClassDetailsDto);
        } else {
            CreatePupilInClassParams params = new CreatePupilInClassParams(
                    requestDto.getSchoolClassId(),
                    requestDto.getPupilId()
            );

            PupilInClass response = pupilInClassService.add(params);
            ResponseEntity<PupilInClassDetailsDto> responseEntity = ResponseEntity.ok(pupilInClassMapper.map(response));

            log.info("Successfully executed add pupil in class rest API, response entity - {}", responseEntity);
            return responseEntity;
        }
    }

    @GetMapping("/classes/{classId}")
    public ResponseEntity<List<PupilInClassDetailsDto>> getPupilsByClassId(@PathVariable("classId") Long classId) {
        log.info("Executing get pupil in class by class id-{}", classId);

        List<PupilInClass> response = pupilInClassService.getBySchoolClassId(classId);
        ResponseEntity<List<PupilInClassDetailsDto>> responseEntity = ResponseEntity.ok(pupilInClassMapper.mapList(response));

        log.info("Successfully executed get pupil in class by class id rest API, response entity - {}", responseEntity);
        return responseEntity;
    }

    @DeleteMapping("/{pupilId}")
    public ResponseEntity<PupilInClassDetailsDto> deletePupilById(@PathVariable("pupilId") Long pupilId) {
        log.info("Executing delete pupil in class by id-{}", pupilId);

        PupilInClass response = pupilInClassService.deleteById(pupilId);

        PupilInClassDetailsDto pupilInClassDetailsDto = pupilInClassMapper.map(response);

        if (response == null) {
            pupilInClassDetailsDto.setErrorType(ErrorType.PUPIL_NOT_FOUND);
        }

        ResponseEntity<PupilInClassDetailsDto> responseEntity = ResponseEntity.ok(pupilInClassDetailsDto);

        log.info("Successfully executed delete pupil in class by id rest API, response entity - {}", responseEntity);
        return responseEntity;
    }
}
