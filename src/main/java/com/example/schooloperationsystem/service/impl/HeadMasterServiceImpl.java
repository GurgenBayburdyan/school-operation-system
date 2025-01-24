package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.repository.HeadMasterRepository;
import com.example.schooloperationsystem.service.HeadMasterService;
import com.example.schooloperationsystem.service.SchoolClassService;
import com.example.schooloperationsystem.service.TeacherService;
import com.example.schooloperationsystem.service.params.CreateHeadMasterParams;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.schooloperationsystem.entity.Teacher;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
class HeadMasterServiceImpl implements HeadMasterService {

    private final HeadMasterRepository repository;
    private final TeacherService teacherService;
    private final SchoolClassService schoolClassService;

    @Override
    @Transactional(readOnly = true)
    public List<HeadMaster> getHeadMasters() {
        log.debug("Executing get all headmasters");

        List<HeadMaster> headMasters = repository.findAll();

        log.debug("Successfully executed get headmasters, {}", headMasters);
        return headMasters;
    }

    @Override
    @Transactional
    public HeadMaster addHeadMaster(CreateHeadMasterParams params) {
        log.debug("Executing add headmaster, params-{}", params);
        HeadMaster headMaster = new HeadMaster();
        Teacher teacher = teacherService.getById(params.getTeacherId());

        SchoolClass schoolClass = schoolClassService.getClassById(params.getSchoolClassId());

        headMaster.setTeacher(teacher);
        headMaster.setSchoolClass(schoolClass);

        log.debug("Successfully executed add headmaster, {}", headMaster);
        return repository.save(headMaster);
    }

}
