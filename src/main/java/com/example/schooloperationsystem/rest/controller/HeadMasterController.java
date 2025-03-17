package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.HeadMasterDetailsDto;
import com.example.schooloperationsystem.rest.facade.HeadMasterFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/headmasters")
@AllArgsConstructor
public class HeadMasterController {

    private final HeadMasterFacade headMasterFacade;

    @GetMapping
    public ResponseEntity<List<HeadMasterDetailsDto>> getAll() {
        log.info("Executing get all headmasters rest API");

        ResponseEntity<List<HeadMasterDetailsDto>> responseEntity = ResponseEntity.ok(headMasterFacade.getAll());

        log.info("Successfully executed get all headmasters, response entity-{}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<HeadMasterDetailsDto> create(@RequestBody CreateHeadMasterRequestDto requestDto) {
        log.info("Executing create headmaster rest API, request-{}", requestDto);

        ResponseEntity<HeadMasterDetailsDto> responseEntity = ResponseEntity.ok(headMasterFacade.create(requestDto));

        log.info("Successfully executed create headmaster, response entity-{}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<HeadMasterDetailsDto> getByTeacherId(@PathVariable("teacherId") Long teacherId) {
        log.info("Executing get headmaster by teacher id rest API, teacher id-{}", teacherId);

        ResponseEntity<HeadMasterDetailsDto> responseEntity = ResponseEntity.ok(headMasterFacade.getByTeacherId(teacherId));

        log.info("Successfully executed get headmaster by teacher id, response entity-{}", responseEntity);
        return responseEntity;
    }
}
