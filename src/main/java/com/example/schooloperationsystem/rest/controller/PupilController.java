package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.controller.validator.PupilValidator;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.mapper.PupilMapper;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import com.example.schooloperationsystem.rest.facade.PupilFacade;
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

    private final PupilFacade pupilFacade;

    @GetMapping
    public ResponseEntity<List<PupilDetailsDto>> getPupils() {
        return ResponseEntity.ok(pupilFacade.getPupils());
    }

    @PostMapping
    public ResponseEntity<PupilDetailsDto> addPupil(@RequestBody CreatePupilRequestDto requestDto) {
        return ResponseEntity.ok(pupilFacade.addPupil(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PupilDetailsDto> deletePupilById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pupilFacade.deletePupilById(id));
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<List<PupilDetailsDto>> getPupilsBySchoolId(@PathVariable("schoolId") Long schoolId) {
        return ResponseEntity.ok(pupilFacade.getPupilsBySchoolId(schoolId));
    }
}
