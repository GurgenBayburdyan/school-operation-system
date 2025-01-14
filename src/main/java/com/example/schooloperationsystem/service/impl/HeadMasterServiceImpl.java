package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.repository.HeadMasterRepository;
import com.example.schooloperationsystem.service.HeadMasterService;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateHeadMasterParams;
import org.springframework.stereotype.Service;
import com.example.schooloperationsystem.entity.Teacher;

import java.util.List;

@Service
public class HeadMasterServiceImpl implements HeadMasterService {
    private final HeadMasterRepository repository;

    private final TeacherService teacherService;
    private final SchoolClassService schoolClassService;

    public HeadMasterServiceImpl(HeadMasterRepository repository, TeacherService teacherService, SchoolClassService schoolClassService) {
        this.repository = repository;
        this.teacherService = teacherService;
        this.schoolClassService = schoolClassService;
    }

    @Override
    public List<HeadMaster> getHeadMasters() {
        return repository.findAll();
    }

    @Override
    public HeadMaster addHeadMaster(CreateHeadMasterParams params) {
        HeadMaster headMaster = new HeadMaster();
        Teacher teacher = teacherService.getTeacherById(params.getTeacherId());
        SchoolClass schoolClass = schoolClassService.getSchoolClassById(params.getClassId());
        headMaster.setTeacher(teacher);
        headMaster.setSchoolClass(schoolClass);
        return repository.save(headMaster);
    }

}
