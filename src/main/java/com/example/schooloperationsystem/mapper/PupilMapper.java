package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.rest.controller.PupilInClassController;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gurgen Bayburdyan
 */

@Slf4j
@Component
public class PupilMapper {

    private static final Logger logger = LoggerFactory.getLogger(PupilMapper.class);

    public PupilDetailsDto map(Pupil pupil) {
        log.trace("Mapping pupil - {} to pupil details dto", pupil);
        PupilDetailsDto detailsDto = new PupilDetailsDto();
        detailsDto.setFirstName(pupil.getFirstName());
        detailsDto.setLastName(pupil.getLastName());
        detailsDto.setDateOfBirth(pupil.getDateOfBirth());
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
}