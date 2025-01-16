package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.repository.TeacherRepository;
import com.example.schooloperationsystem.service.StaffService;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateTeacherParams;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;
    private final StaffService staffService;

    private static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Override
    public List<Teacher> getTeachers() {
        logger.info("Executing get all teachers");

        List<Teacher> teachers = repository.findAll();

        logger.info("Successfully executed get all teachers, {}", teachers);
        return teachers;
    }

    @Override
    @Transactional
    public Teacher create(CreateTeacherParams params) {
        logger.info("Executing create teacher, params-{}", params);

        Teacher teacher = new Teacher();

        Staff staff = staffService.getById(params.getStaffId());
        if (staff != null) {
            teacher.setStaff(staff);
            logger.info("Successfully found and set staff for teacher, staff-{}", staff);
        } else {
            logger.warn("No staff found with id-{}", params.getStaffId());
        }

        Teacher savedTeacher = repository.save(teacher);
        logger.info("Successfully created teacher, {}", savedTeacher);
        return savedTeacher;
    }

    @Override
    @Transactional
    public Teacher getById(Long id) {
        logger.info("Executing get teacher by id, id-{}", id);

        Teacher teacher = repository.findById(id).orElse(null);

        if (teacher != null) {
            logger.info("Successfully executed get teacher by id, {}", teacher);
        } else {
            logger.info("No teacher found with id-{}", id);
        }

        return teacher;
    }
}
