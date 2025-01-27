package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.repository.TeacherRepository;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateTeacherParams;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;
    private final StaffService staffService;

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> get() {
        log.debug("Executing get all teachers");

        List<Teacher> teachers = repository.findAll();

        log.debug("Successfully executed get all teachers, {}", teachers);
        return teachers;
    }

    @Override
    @Transactional
    public Teacher create(CreateTeacherParams params) {
        log.debug("Executing create teacher, params-{}", params);

        Teacher teacher = new Teacher();

        Staff staff = staffService.getById(params.getStaffId());

        teacher.setStaff(staff);

        Teacher savedTeacher = repository.save(teacher);
        log.debug("Successfully created teacher, {}", savedTeacher);
        return savedTeacher;
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher getById(Long id) {
        log.debug("Executing get teacher by id, id-{}", id);

        Teacher teacher = repository.findById(id).orElse(null);

        if (teacher != null) {
            log.debug("Successfully executed get teacher by id, {}", teacher);
        } else {
            log.debug("No teacher found with id-{}", id);
        }

        return teacher;
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}