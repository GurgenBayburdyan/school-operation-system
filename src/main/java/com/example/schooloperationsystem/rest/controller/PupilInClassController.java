package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.entity.SchoolClass;
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
        log.info("Executing get all pupils in classes rest API");
        List<PupilInClass> response = pupilInClassService.getPupilInClasses();
        log.info("Got pupils in classes {}", response);
        return ResponseEntity.ok(pupilInClassMapper.mapList(response));
    }

    @PostMapping
    public ResponseEntity<PupilInClassDetailsDto> addPupilInClass(@RequestBody CreatePupilInClassRequestDto requestDto) {
        log.info("Executing add pupil in class rest API");
        CreatePupilInClassParams params = new CreatePupilInClassParams();
        Pupil pupil = new Pupil();
        pupil.setFirstName(requestDto.getPupilDto().getFirstName());
        pupil.setLastName(requestDto.getPupilDto().getLastName());
        pupil.setDateOfBirth(requestDto.getPupilDto().getDateOfBirth());
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setGrade(requestDto.getSchoolClassDto().getGrade());
        schoolClass.setLetter(requestDto.getSchoolClassDto().getLetter());
        params.setPupil(pupil);
        params.setSchoolClass(schoolClass);
        PupilInClass response = pupilInClassService.addPupil(params);
        log.info("Saved pupil in class {}", response);
        return ResponseEntity.ok(pupilInClassMapper.map(response));
    }
}
