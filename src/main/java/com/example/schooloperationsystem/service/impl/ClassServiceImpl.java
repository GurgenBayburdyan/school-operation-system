package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.repository.ClassRepository;
import com.example.schooloperationsystem.service.ClassService;
import com.example.schooloperationsystem.service.params.CreateClassParams;
import com.example.schooloperationsystem.entity.Class;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Gurgen Bayburdyan
 */
@Service
@AllArgsConstructor
public class ClassServiceImpl implements ClassService {

    private ClassRepository repository;

    @Override
    public List<Class> getClasses() {
        return repository.findAll();
    }

    @Override
    public Class addClass(CreateClassParams params) {
        Class classEntity = new Class();
        classEntity.setGrade(params.getGrade());
        classEntity.setClassLetter(params.getClassLetter());
        return repository.save(classEntity);
    }
}
