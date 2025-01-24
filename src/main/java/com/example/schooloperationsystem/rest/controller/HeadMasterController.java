package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.controller.validator.HeadMasterValidator;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.mapper.HeadMasterMapper;
import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.HeadMasterDetailsDto;
import com.example.schooloperationsystem.service.HeadMasterService;
import com.example.schooloperationsystem.service.params.CreateHeadMasterParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/headmasters")
@AllArgsConstructor
public class HeadMasterController {

    private final HeadMasterService headMasterService;
    private final HeadMasterMapper headMasterMapper;
    private final HeadMasterValidator headMasterValidator;

    @GetMapping
    public ResponseEntity<List<HeadMasterDetailsDto>> getAllHeadMasters() {
        log.info("Executing get all head masters rest API");

        List<HeadMaster> response = headMasterService.getHeadMasters();
        ResponseEntity<List<HeadMasterDetailsDto>> responseEntity = ResponseEntity.ok(headMasterMapper.mapList(response));

        log.info("Successfully executed get all head masters rest API, response entity - {}", responseEntity);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<HeadMasterDetailsDto> create(@RequestBody CreateHeadMasterRequestDto requestDto) {
        log.info("Executing create head master for the provided request to - {}:", requestDto);

        Optional<ErrorType> optionalErrorType = headMasterValidator.validateCreate(requestDto);
        if (optionalErrorType.isEmpty()) {
            CreateHeadMasterParams params = new CreateHeadMasterParams(
                    requestDto.getTeacherId(),
                    requestDto.getClassId()
            );

            HeadMaster headMaster = headMasterService.addHeadMaster(params);
            ResponseEntity<HeadMasterDetailsDto> responseEntity = ResponseEntity.ok(headMasterMapper.map(headMaster));

            log.info("Successfully executed create head master rest API, response entity - {}", responseEntity);
            return responseEntity;
        }

        HeadMasterDetailsDto headMasterDetailsDto = new HeadMasterDetailsDto();
        headMasterDetailsDto.setErrorType(optionalErrorType.get());
        log.info("Executing create headmaster failed, error-{}", optionalErrorType.get());
        return ResponseEntity.ok(headMasterDetailsDto);
    }
}
