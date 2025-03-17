package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.repository.TeacherRepository;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateTeacherParams;
import jakarta.persistence.EntityNotFoundException;
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

        Staff staff = staffService.findById(params.getStaffId());

        teacher.setStaff(staff);

        Teacher savedTeacher = repository.save(teacher);
        log.debug("Successfully created teacher, {}", savedTeacher);
        return savedTeacher;
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher findById(Long id) {
        log.debug("Executing get teacher by id, id-{}", id);

        Teacher teacher = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Teacher not found")
        );

        log.debug("Successfully executed get teacher by id, {}", teacher);
        return teacher;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    @Transactional
    public Teacher findByStaffId(Long staffId) {
        log.debug("Executing get teacher by staff id, id-{}", staffId);

        Teacher teacher = repository.findByStaff_Id(staffId).orElseThrow(
                () -> new EntityNotFoundException("Teacher not found")
        );

        log.debug("Successfully executed get teacher by staff id, {}", teacher);
        return teacher;
    }
}