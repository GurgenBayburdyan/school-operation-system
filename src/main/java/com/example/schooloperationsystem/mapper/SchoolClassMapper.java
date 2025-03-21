package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.School;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.rest.dto.request.CreateSchoolClassRequestDto;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;
import com.example.schooloperationsystem.service.params.CreateSchoolClassParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class SchoolClassMapper {

    public SchoolClassDetailsDto map(SchoolClass classEntity) {
        log.trace("Mapping class - {} to class details dto", classEntity);

        SchoolClassDetailsDto classDetailsDto = new SchoolClassDetailsDto();
        School school = classEntity.getSchool();

        classDetailsDto.setId(classEntity.getId());
        classDetailsDto.setClassLetter(classEntity.getLetter());
        classDetailsDto.setGrade(classEntity.getGrade());
        classDetailsDto.setSchoolId(school.getId());

        log.trace("Mapped class {}", classDetailsDto);
        return classDetailsDto;
    }

    public List<SchoolClassDetailsDto> mapList(List<SchoolClass> classEntities) {
        List<SchoolClassDetailsDto> classDetailsDtos = new ArrayList<>();
        for (SchoolClass classEntity : classEntities) {
            classDetailsDtos.add(map(classEntity));
        }
        return classDetailsDtos;
    }

    public CreateSchoolClassParams fromRequestDtoToParams(CreateSchoolClassRequestDto requestDto) {
        return new CreateSchoolClassParams(requestDto.getClassLetter(), requestDto.getGrade(), requestDto.getSchoolId());
    }
}