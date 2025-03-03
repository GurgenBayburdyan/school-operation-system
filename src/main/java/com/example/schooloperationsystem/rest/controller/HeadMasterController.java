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
    public ResponseEntity<List<HeadMasterDetailsDto>> getAllHeadMasters() {
        return ResponseEntity.ok(headMasterFacade.getAllHeadMasters());
    }

    @PostMapping
    public ResponseEntity<HeadMasterDetailsDto> create(@RequestBody CreateHeadMasterRequestDto requestDto) {
        return ResponseEntity.ok(headMasterFacade.create(requestDto));
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<HeadMasterDetailsDto> getHeadMasterByTeacherId(@PathVariable("teacherId") Long teacherId) {
        return ResponseEntity.ok(headMasterFacade.getHeadMasterByTeacherId(teacherId));
    }
}
