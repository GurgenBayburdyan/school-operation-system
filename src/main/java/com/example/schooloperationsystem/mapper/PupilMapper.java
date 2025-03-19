package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.rest.dto.request.CreatePupilRequestDto;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import com.example.schooloperationsystem.service.params.CreatePupilParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PupilMapper {

    public PupilDetailsDto map(Pupil pupil) {
        log.trace("Mapping pupil - {} to pupil details dto", pupil);

        PupilDetailsDto detailsDto = new PupilDetailsDto();
        detailsDto.setId(pupil.getId());
        detailsDto.setFirstName(pupil.getFirstName());
        detailsDto.setLastName(pupil.getLastName());
        detailsDto.setDateOfBirth(pupil.getDateOfBirth());
        detailsDto.setSchoolId(pupil.getSchool().getId());

        log.trace("Mapped pupil to {}", detailsDto);
        return detailsDto;
    }

    public List<PupilDetailsDto> mapList(List<Pupil> pupils) {
        List<PupilDetailsDto> detailsDtos = new ArrayList<>();
        for (Pupil pupil : pupils) {
            detailsDtos.add(map(pupil));
        }
        return detailsDtos;
    }

    public CreatePupilParams fromRequestDtoToParams(CreatePupilRequestDto requestDto) {
        return new CreatePupilParams(
                requestDto.getFirstName(),
                requestDto.getLastName(),
                requestDto.getDateOfBirth(),
                requestDto.getSchoolId()
        );
    }
}