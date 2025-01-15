package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.repository.HeadMasterRepository;
import com.example.schooloperationsystem.rest.dto.SchoolClassDto;
import com.example.schooloperationsystem.rest.dto.TeacherDto;
import com.example.schooloperationsystem.service.HeadMasterService;
import com.example.schooloperationsystem.service.params.CreateHeadMasterParams;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.schooloperationsystem.entity.Teacher;

import java.util.List;

@Service
class HeadMasterServiceImpl implements HeadMasterService {
    private final HeadMasterRepository repository;

    public HeadMasterServiceImpl(HeadMasterRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<HeadMaster> getHeadMasters() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public HeadMaster addHeadMaster(CreateHeadMasterParams params) {
        HeadMaster headMaster = new HeadMaster();
        Teacher teacher = new Teacher();

        SchoolClass schoolClass = new SchoolClass();

        TeacherDto teacherDto = params.getTeacherDto();
        SchoolClassDto schoolClassDto = params.getSchoolClassDto();

        Staff staff=new Staff();
        staff.setFirstName(teacherDto.getStaffDto().getFirstName());
        staff.setLastName(teacherDto.getStaffDto().getLastName());
        staff.setDateOfBirth(teacherDto.getStaffDto().getDateOfBirth());

        schoolClass.setGrade(schoolClassDto.getGrade());
        schoolClass.setLetter(schoolClassDto.getClassLetter());

        teacher.setStaff(staff);
        headMaster.setTeacher(teacher);
        headMaster.setSchoolClass(schoolClass);
        return repository.save(headMaster);
    }

}