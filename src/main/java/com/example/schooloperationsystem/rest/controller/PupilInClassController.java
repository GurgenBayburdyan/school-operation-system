package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.controller.validator.PupilInClassValidator;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.mapper.PupilInClassMapper;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import com.example.schooloperationsystem.rest.dto.response.PupilInClassDetailsDto;
import com.example.schooloperationsystem.rest.facade.PupilInClassFacade;
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

    private final PupilInClassFacade pupilInClassFacade;

    @GetMapping
    public ResponseEntity<List<PupilInClassDetailsDto>> getPupilsInClass() {
        return ResponseEntity.ok(pupilInClassFacade.getPupilsInClass());
    }

    @PostMapping
    public ResponseEntity<PupilInClassDetailsDto> addPupilInClass(@RequestBody CreatePupilInClassRequestDto requestDto) {
        return ResponseEntity.ok(pupilInClassFacade.addPupilInClass(requestDto));
    }

    @GetMapping("/classes/{classId}")
    public ResponseEntity<List<PupilInClassDetailsDto>> getPupilsByClassId(@PathVariable("classId") Long classId) {
        return ResponseEntity.ok(pupilInClassFacade.getPupilsByClassId(classId));
    }

    @DeleteMapping("/{pupilId}")
    public ResponseEntity<PupilInClassDetailsDto> deletePupilByPupilId(@PathVariable("pupilId") Long pupilId) {
        return ResponseEntity.ok(pupilInClassFacade.deletePupilByPupilId(pupilId));
    }

    @GetMapping("/{pupilId}")
    public ResponseEntity<PupilInClassDetailsDto> getPupilInClassByPupilId(@PathVariable("pupilId") Long pupilId) {
        return ResponseEntity.ok(pupilInClassFacade.getPupilInClassByPupilId(pupilId));
    }
}
