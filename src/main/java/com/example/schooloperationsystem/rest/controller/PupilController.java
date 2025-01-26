package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.controller.validator.PupilValidator;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.mapper.PupilMapper;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.params.CreatePupilParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/pupils")
public class PupilController {

    private final PupilService pupilService;
    private final PupilMapper pupilMapper;
    private final PupilValidator pupilValidator;

    @GetMapping
    public ResponseEntity<List<PupilDetailsDto>> getPupils() {
        log.info("Executing get all pupils rest API");


        //todo please add final keword wherever possible List<Pupil> response = pupilService.getPupils();
        ResponseEntity<List<PupilDetailsDto>> responseEntity = ResponseEntity.ok(pupilMapper.mapList(response));

        log.info("Successfully executed get all pupils rest API, response entity - {}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<PupilDetailsDto> addPupil(@RequestBody CreatePupilRequestDto requestDto) {
        log.info("Executing add pupil for the provided request to - {}:", requestDto);

        Optional<ErrorType> optionalErrorType = pupilValidator.validateCreate(requestDto);

        if (optionalErrorType.isEmpty()) {
            CreatePupilParams params = new CreatePupilParams(
                    requestDto.getFirstName(),
                    requestDto.getLastName(),
                    requestDto.getDateOfBirth()
            );

            Pupil response = pupilService.addPupil(params);
            ResponseEntity<PupilDetailsDto> responseEntity = ResponseEntity.ok(pupilMapper.map(response));

            log.info("Successfully executed add pupil rest API, response entity - {}", responseEntity);
            return responseEntity;
        }

        //move the below code right after validator
        PupilDetailsDto pupilDetailsDto = new PupilDetailsDto();
        pupilDetailsDto.setErrorType(optionalErrorType.get());
        log.info("Executing create pupil failed, error-{}", optionalErrorType.get());
        return ResponseEntity.ok(pupilDetailsDto);
    }
}
