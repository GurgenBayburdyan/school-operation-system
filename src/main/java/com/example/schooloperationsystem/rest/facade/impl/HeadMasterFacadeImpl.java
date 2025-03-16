package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.mapper.HeadMasterMapper;
import com.example.schooloperationsystem.rest.facade.validator.HeadMasterValidator;
import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.HeadMasterDetailsDto;
import com.example.schooloperationsystem.rest.facade.HeadMasterFacade;
import com.example.schooloperationsystem.service.HeadMasterService;
import com.example.schooloperationsystem.service.params.CreateHeadMasterParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
class HeadMasterFacadeImpl implements HeadMasterFacade {

    private final HeadMasterService headMasterService;
    private final HeadMasterMapper headMasterMapper;
    private final HeadMasterValidator headMasterValidator;

    @Override
    public List<HeadMasterDetailsDto> getAllHeadMasters() {
        log.info("Executing get all head masters rest API");

        List<HeadMaster> response = headMasterService.get();
        List<HeadMasterDetailsDto> headMasterDetailsDtos = headMasterMapper.mapList(response);

        log.info("Successfully executed get all head masters rest API, response - {}", headMasterDetailsDtos);
        return headMasterDetailsDtos;
    }

    @Override
    public HeadMasterDetailsDto create(CreateHeadMasterRequestDto requestDto) {
        log.info("Executing create head master for the provided request to - {}:", requestDto);

        Optional<ErrorType> optionalErrorType = headMasterValidator.validateCreate(requestDto);

        if (optionalErrorType.isPresent()) {
            HeadMasterDetailsDto headMasterDetailsDto = new HeadMasterDetailsDto(optionalErrorType.get());
            log.info("Executing create headmaster failed, error-{}", optionalErrorType.get());
            return headMasterDetailsDto;
        } else {
            CreateHeadMasterParams params = new CreateHeadMasterParams(
                    requestDto.getTeacherId(),
                    requestDto.getClassId()
            );

            HeadMaster headMaster = headMasterService.add(params);
            HeadMasterDetailsDto headMasterDetailsDto = headMasterMapper.map(headMaster);

            log.info("Successfully executed create head master rest API, response - {}", headMasterDetailsDto);
            return headMasterDetailsDto;
        }
    }

    @Override
    public HeadMasterDetailsDto getHeadMasterByTeacherId(Long teacherId) {
        log.info("Executing get head master by teacher id-{}", teacherId);

        HeadMaster response=headMasterService.findByTeacherId(teacherId);

        if (response == null) {
            HeadMasterDetailsDto headMasterDetailsDto=new HeadMasterDetailsDto();
            headMasterDetailsDto.setErrorType(ErrorType.HEAD_MASTER_NOT_FOUND);
            return headMasterDetailsDto;
        }

        HeadMasterDetailsDto headMasterDetailsDto=headMasterMapper.map(response);

        log.info("Successfully executed get head master by teacher id rest API, response - {}", headMasterDetailsDto);
        return headMasterDetailsDto;
    }

}
