package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.mapper.HeadMasterMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.HeadMasterDetailsDto;
import com.example.schooloperationsystem.service.HeadMasterService;
import com.example.schooloperationsystem.service.params.CreateHeadMasterParams;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/headmasters")
public class HeadMasterController {

    private static final Logger logger = LoggerFactory.getLogger(HeadMasterController.class);

    private final HeadMasterService service;
    private final HeadMasterMapper mapper;

    public HeadMasterController(HeadMasterService service, HeadMasterMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<HeadMasterDetailsDto> getAllHeadMasters() {
        logger.info("Getting HeadMasters...");
        List<HeadMaster> headMasters = service.getHeadMasters();
        logger.info("Got HeadMasters {}", headMasters);
        return mapper.mapList(headMasters);
    }

    @PostMapping
    public HeadMasterDetailsDto createHeadMaster(@RequestBody CreateHeadMasterRequestDto requestDto) {
        logger.info("Creating HeadMaster with teacherId: {} and classId: {}",
                requestDto.getTeacherId(),
                requestDto.getClassId());
        CreateHeadMasterParams params = new CreateHeadMasterParams();
        params.setTeacherId(requestDto.getTeacherId());
        params.setClassId(requestDto.getClassId());
        HeadMaster headMaster = service.addHeadMaster(params);
        logger.info("Created HeadMaster {}", headMaster);
        return mapper.map(headMaster);
    }
}
