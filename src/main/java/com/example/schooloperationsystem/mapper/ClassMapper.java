package com.example.schooloperationsystem.mapper;

import com.example.schooloperationsystem.entity.Class;
import com.example.schooloperationsystem.rest.dto.response.ClassDetailsDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gurgen Bayburdyan
 */
@Component
public class ClassMapper {
    public ClassDetailsDto map(Class classEntity) {
        ClassDetailsDto classDetailsDto = new ClassDetailsDto();
        classDetailsDto.setClassLetter(classEntity.getClassLetter());
        classDetailsDto.setGrade(classEntity.getGrade());
        return classDetailsDto;
    }

    public List<ClassDetailsDto> mapList(List<Class> classEntities) {
        List<ClassDetailsDto> classDetailsDtos = new ArrayList<>();
        for (Class classEntity : classEntities) {
            classDetailsDtos.add(map(classEntity));
        }
        return classDetailsDtos;
    }
}
