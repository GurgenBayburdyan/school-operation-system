package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import com.example.schooloperationsystem.repository.TeacherRepository;
import com.example.schooloperationsystem.rest.dto.StaffDto;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateTeacherParams;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;

    public TeacherServiceImpl(TeacherRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Teacher> getTeachers() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Teacher addTeacher(CreateTeacherParams params) {
        Teacher teacher = new Teacher();
        Staff staff = new Staff();
        StaffDto staffDetailsDto = params.getStaffDto();
        staff.setFirstName(staffDetailsDto.getFirstName());
        staff.setLastName(staffDetailsDto.getLastName());
        staff.setDateOfBirth(staffDetailsDto.getDateOfBirth());
        teacher.setStaff(staff);
        return repository.save(teacher);
    }

    @Override
    @Transactional
    public Teacher getTeacherById(Long id) {
        return repository.getReferenceById(id);
    }
}
