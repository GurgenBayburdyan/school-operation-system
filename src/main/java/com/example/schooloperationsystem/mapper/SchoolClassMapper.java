package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.rest.dto.response.SchoolClassDetailsDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gurgen Bayburdyan
 */
@Component
public class SchoolClassMapper {
    public SchoolClassDetailsDto map(SchoolClass classEntity) {
        SchoolClassDetailsDto classDetailsDto = new SchoolClassDetailsDto();
        classDetailsDto.setClassLetter(classEntity.getClassLetter());
        classDetailsDto.setGrade(classEntity.getGrade());
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
