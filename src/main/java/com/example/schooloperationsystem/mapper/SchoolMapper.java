package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.School;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolRequestDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolDetailsDto;
import com.example.schooloperationsystem.service.params.CreateSchoolParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SchoolMapper {

    public SchoolDetailsDto map(School school) {
        log.trace("Mapping school - {} to school details dto", school);

        SchoolDetailsDto detailsDto = new SchoolDetailsDto();
        detailsDto.setId(school.getId());
        detailsDto.setNumber(school.getNumber());
        detailsDto.setNamedAfter(school.getNamedAfter());
        detailsDto.setAddress(school.getAddress());
        detailsDto.setPhotoUrl(school.getPhotoUrl());

        log.trace("Mapped school to {}", detailsDto);
        return detailsDto;
    }

    public List<SchoolDetailsDto> mapList(List<School> schools) {
        List<SchoolDetailsDto> detailsDtos = new ArrayList<>();
        for (School school : schools) {
            detailsDtos.add(map(school));
        }
        return detailsDtos;
    }

    public CreateSchoolParams fromRequestDtoToParams(CreateSchoolRequestDto requestDto) {
        return new CreateSchoolParams(requestDto.getNumber(), requestDto.getNamedAfter(), requestDto.getAddress(), requestDto.getPhotoUrl());
    }
}
