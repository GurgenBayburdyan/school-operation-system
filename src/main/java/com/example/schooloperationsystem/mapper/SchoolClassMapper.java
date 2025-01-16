package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SchoolClassMapper {

    private static final Logger logger = LoggerFactory.getLogger(SchoolClassMapper.class);

    public SchoolClassDetailsDto map(SchoolClass classEntity) {
        logger.trace("Mapping class - {} to class details dto", classEntity);

        SchoolClassDetailsDto classDetailsDto = new SchoolClassDetailsDto();
        classDetailsDto.setClassLetter(classEntity.getLetter());
        classDetailsDto.setGrade(classEntity.getGrade());

        logger.trace("Mapped class {}", classDetailsDto);
        return classDetailsDto;
    }

    public List<SchoolClassDetailsDto> mapList(List<SchoolClass> classEntities) {
        List<SchoolClassDetailsDto> classDetailsDtos = new ArrayList<>();
        for (SchoolClass classEntity : classEntities) {
            classDetailsDtos.add(map(classEntity));
        }
        return classDetailsDtos;
    }
}
