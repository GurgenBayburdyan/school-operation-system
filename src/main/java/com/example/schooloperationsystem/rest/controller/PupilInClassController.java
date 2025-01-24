package com.example.schooloperationsystem.rest.controller;

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

    @GetMapping
    public ResponseEntity<List<PupilInClassDetailsDto>> getPupilsInClass() {
        log.info("Executing get all pupils in classes rest API");

        List<PupilInClass> response = pupilInClassService.getPupilInClasses();
        ResponseEntity<List<PupilInClassDetailsDto>> responseEntity = ResponseEntity.ok(pupilInClassMapper.mapList(response));

        log.info("Successfully executed get pupils in class rest API, response entity - {}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<PupilInClassDetailsDto> addPupilInClass(@RequestBody CreatePupilInClassRequestDto requestDto) {
        log.info("Executing add pupil in class for the provided request to-{}", requestDto);

        PupilInClassDetailsDto pupilInClassDetailsDto = new PupilInClassDetailsDto();

        Optional<ErrorType> optionalErrorType = validateCreate(requestDto);

        if (optionalErrorType.isPresent()) {
            pupilInClassDetailsDto.setErrorType(optionalErrorType.get());
        } else {
            CreatePupilInClassParams params = new CreatePupilInClassParams(
                    requestDto.getSchoolClassId(),
                    requestDto.getPupilId()
            );

            PupilInClass response = pupilInClassService.addPupil(params);
            ResponseEntity<PupilInClassDetailsDto> responseEntity = ResponseEntity.ok(pupilInClassMapper.map(response));

            log.info("Successfully executed add pupil in class rest API, response entity - {}", responseEntity);
            return responseEntity;
        }

        return ResponseEntity.ok(pupilInClassDetailsDto);
    }

    private Optional<ErrorType> validateCreate(CreatePupilInClassRequestDto requestDto) {
        if (requestDto.getPupilId() == null) {
            return Optional.of(ErrorType.MISSING_PUPIL_ID);
        } else if (requestDto.getSchoolClassId() == null) {
            return Optional.of(ErrorType.MISSING_SCHOOL_CLASS_ID);
        }
        return Optional.empty();
    }

    //todo please change mapping to /classes/{classId}
    @GetMapping("/{classId}")
    public ResponseEntity<List<PupilInClassDetailsDto>> getPupilsByClassId(@PathVariable("classId") Long classId) {
        log.info("Executing get pupil in class by class id-{}", classId);

        List<PupilInClass> response = pupilInClassService.getPupilsBySchoolClassId(classId);
        //todo why Optional ?
        ResponseEntity<List<PupilInClassDetailsDto>> responseEntity = ResponseEntity.of(Optional.ofNullable(pupilInClassMapper.mapList(response)));

        log.info("Successfully executed get pupil in class by class id rest API, response entity - {}", responseEntity);
        return responseEntity;
    }

    @DeleteMapping("/{pupilId}")
    public ResponseEntity<PupilInClassDetailsDto> deletePupilById(@PathVariable("pupilId") Long pupilId) {
        log.info("Executing delete pupil in class by id-{}", pupilId);

        PupilInClass response = pupilInClassService.deletePupilById(pupilId);
        ResponseEntity<PupilInClassDetailsDto> responseEntity = ResponseEntity.ok(pupilInClassMapper.map(response));

        log.info("Successfully executed delete pupil in class by id rest API, response entity - {}", responseEntity);
        return responseEntity;
    }
}
