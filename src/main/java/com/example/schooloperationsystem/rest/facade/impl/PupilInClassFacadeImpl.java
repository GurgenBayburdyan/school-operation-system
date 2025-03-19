package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.mapper.PupilInClassMapper;
import com.example.schooloperationsystem.rest.facade.validator.PupilInClassValidator;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilInClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.PupilInClassDetailsDto;
import com.example.schooloperationsystem.rest.facade.PupilInClassFacade;
import com.example.schooloperationsystem.service.PupilInClassService;
import com.example.schooloperationsystem.service.params.CreatePupilInClassParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
class PupilInClassFacadeImpl implements PupilInClassFacade {

    private final PupilInClassService pupilInClassService;
    private final PupilInClassMapper pupilInClassMapper;
    private final PupilInClassValidator pupilInClassValidator;

    @Override
    public List<PupilInClassDetailsDto> getAll() {
        log.info("Executing get all pupils in classes rest API");

        List<PupilInClass> response = pupilInClassService.get();
        List<PupilInClassDetailsDto> pupilInClassDetailsDtos = pupilInClassMapper.mapList(response);

        log.info("Successfully executed get pupils in class rest API, response  - {}", pupilInClassDetailsDtos);
        return pupilInClassDetailsDtos;
    }

    @Override
    public PupilInClassDetailsDto create(CreatePupilInClassRequestDto requestDto) {
        log.info("Executing add pupil in class for the provided request to-{}", requestDto);

        Optional<ErrorType> optionalErrorType = pupilInClassValidator.validateCreate(requestDto);

        if (optionalErrorType.isPresent()) {
            PupilInClassDetailsDto pupilInClassDetailsDto = new PupilInClassDetailsDto(optionalErrorType.get());
            log.info("Executing create pupil in class failed, error-{}", optionalErrorType.get());
            return pupilInClassDetailsDto;
        } else {
            CreatePupilInClassParams params = pupilInClassMapper.fromRequestDtoToParams(requestDto);

            PupilInClass response = pupilInClassService.add(params);
            PupilInClassDetailsDto pupilInClassDetailsDto = pupilInClassMapper.map(response);

            log.info("Successfully executed add pupil in class rest API, response - {}", pupilInClassDetailsDto);
            return pupilInClassDetailsDto;
        }
    }

    @Override
    public List<PupilInClassDetailsDto> getByClassId(Long classId) {
        log.info("Executing get pupil in class by class id-{}", classId);

        List<PupilInClass> response = pupilInClassService.findBySchoolClassId(classId);
        List<PupilInClassDetailsDto> pupilInClassDetailsDtos = pupilInClassMapper.mapList(response);

        log.info("Successfully executed get pupil in class by class id rest API, response - {}", pupilInClassDetailsDtos);
        return pupilInClassDetailsDtos;
    }

    @Override
    public PupilInClassDetailsDto deleteByPupilId(Long pupilId) {
        log.info("Executing delete pupil in class by id-{}", pupilId);

        PupilInClass response = pupilInClassService.deleteByPupilId(pupilId);


        if (response == null) {
            PupilInClassDetailsDto pupilInClassDetailsDto = new PupilInClassDetailsDto();
            pupilInClassDetailsDto.setErrorType(ErrorType.PUPIL_NOT_FOUND);
            log.info("Executing delete pupil in class by pupil id failed, error-{}", pupilInClassDetailsDto.getErrorType());
            return pupilInClassDetailsDto;
        }

        PupilInClassDetailsDto pupilInClassDetailsDto = pupilInClassMapper.map(response);

        log.info("Successfully executed delete pupil in class by id rest API, response - {}", pupilInClassDetailsDto);
        return pupilInClassDetailsDto;
    }

    @Override
    public PupilInClassDetailsDto getByPupilId(Long pupilId) {
        log.info("Executing get pupil in class by pupil id-{}", pupilId);

        PupilInClass response = pupilInClassService.findByPupilId(pupilId);

        if (response == null) {
            PupilInClassDetailsDto pupilInClassDetailsDto = new PupilInClassDetailsDto();
            pupilInClassDetailsDto.setErrorType(ErrorType.PUPIL_IN_CLASS_NOT_FOUND);
            log.info("Executing get pupil in class by pupil id failed, error-{}", pupilInClassDetailsDto.getErrorType());
            return pupilInClassDetailsDto;
        }

        PupilInClassDetailsDto pupilInClassDetailsDto = pupilInClassMapper.map(response);

        log.info("Successfully executed get pupil in class by pupil id rest API, response - {}", pupilInClassDetailsDto);
        return pupilInClassDetailsDto;
    }
}
