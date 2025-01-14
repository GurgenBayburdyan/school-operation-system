package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.mapper.HeadMasterMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.HeadMasterDetailsDto;
import com.example.schooloperationsystem.service.HeadMasterService;
import com.example.schooloperationsystem.service.params.CreateHeadMasterParams;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/headmasters")
public class HeadMasterController {

    private final HeadMasterService service;
    private final HeadMasterMapper mapper;

    public HeadMasterController(HeadMasterService service, HeadMasterMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<HeadMasterDetailsDto> getAllHeadMasters() {
        List<HeadMaster> headMasters = service.getHeadMasters();
        return mapper.mapList(headMasters);
    }

    @PostMapping
    public HeadMasterDetailsDto createHeadMaster(@RequestBody CreateHeadMasterRequestDto requestDto) {
        CreateHeadMasterParams params = new CreateHeadMasterParams();
        params.setTeacherId(requestDto.getTeacherId());
        params.setClassId(params.getClassId());
        HeadMaster headMaster = service.addHeadMaster(params);
        return mapper.map(headMaster);
    }
}
