package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.mapper.PupilMapper;
import com.example.schooloperationsystem.rest.facade.validator.PupilValidator;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.ErrorType;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import com.example.schooloperationsystem.rest.facade.PupilFacade;
import com.example.schooloperationsystem.service.PupilService;
import com.example.schooloperationsystem.service.params.CreatePupilParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
class PupilFacadeImpl implements PupilFacade {

    private final PupilService pupilService;
    private final PupilMapper pupilMapper;
    private final PupilValidator pupilValidator;

    @Override
    public List<PupilDetailsDto> getAll() {
        log.info("Executing get all pupils rest API");

        final List<Pupil> response = pupilService.get();

        List<PupilDetailsDto> pupilDetailsDtos = pupilMapper.mapList(response);

        log.info("Successfully executed get all pupils rest API, response - {}", pupilDetailsDtos);
        return pupilDetailsDtos;
    }

    @Override
    public PupilDetailsDto create(CreatePupilRequestDto requestDto) {
        log.info("Executing add pupil for the provided request to - {}:", requestDto);
        final Optional<ErrorType> optionalErrorType = pupilValidator.validateCreate(requestDto);

        if (optionalErrorType.isPresent()) {
            PupilDetailsDto pupilDetailsDto = new PupilDetailsDto(optionalErrorType.get());
            log.info("Executing create pupil failed, error-{}", optionalErrorType.get());
            return pupilDetailsDto;
        } else {
            final CreatePupilParams params = new CreatePupilParams(
                    requestDto.getFirstName(),
                    requestDto.getLastName(),
                    requestDto.getDateOfBirth(),
                    requestDto.getSchoolId()
            );

            final Pupil response = pupilService.add(params);
            log.info("Successfully executed add pupil rest API, response - {}", response);
            return pupilMapper.map(response);
        }
    }

    @Override
    public PupilDetailsDto deleteById(Long id) {
        log.info("Executing delete pupil by id, id - {}:", id);

        Pupil response = pupilService.deleteById(id);

        if (response == null) {
            PupilDetailsDto pupilDetailsDto = new PupilDetailsDto();
            pupilDetailsDto.setErrorType(ErrorType.PUPIL_NOT_FOUND);
            return pupilDetailsDto;
        }

        PupilDetailsDto pupilDetailsDto = pupilMapper.map(response);

        log.info("Successfully executed delete pupil rest API, response - {}", pupilDetailsDto);
        return pupilDetailsDto;
    }

    @Override
    public List<PupilDetailsDto> getBySchoolId(Long schoolId) {
        log.info("Executing get all pupils by school id rest API, school id-{}", schoolId);

        final List<Pupil> response = pupilService.findBySchoolId(schoolId);

        List<PupilDetailsDto> pupilDetailsDtos = pupilMapper.mapList(response);

        log.info("Successfully executed get all pupils by school id rest API, response - {}", pupilDetailsDtos);
        return pupilDetailsDtos;
    }
}
