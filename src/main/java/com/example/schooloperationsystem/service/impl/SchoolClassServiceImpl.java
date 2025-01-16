package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.repository.SchoolClassRepository;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.params.SchoolCreateClassParams;
import com.example.schooloperationsystem.entity.SchoolClass;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Gurgen Bayburdyan
 */
@Service
@AllArgsConstructor
class SchoolClassServiceImpl implements SchoolClassService {

    private SchoolClassRepository repository;

    @Override
    @Transactional
    public List<SchoolClass> getClasses() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public SchoolClass addClass(SchoolCreateClassParams params) {
        SchoolClass classEntity = new SchoolClass();
        classEntity.setGrade(params.getGrade());
        classEntity.setLetter(params.getClassLetter());
        return repository.save(classEntity);
    }

    @Override
    @Transactional
    public SchoolClass getClassById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
