package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.entity.SchoolClass;
import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.repository.HeadMasterRepository;
import com.example.schooloperationsystem.rest.dto.SchoolClassDto;
import com.example.schooloperationsystem.rest.dto.TeacherDto;
import com.example.schooloperationsystem.service.HeadMasterService;
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
        Teacher teacher = new Teacher();

        SchoolClass schoolClass = new SchoolClass();

        TeacherDto teacherDto = params.getTeacherDto();
        SchoolClassDto schoolClassDto = params.getSchoolClassDto();

        Staff staff = new Staff();
        staff.setFirstName(teacherDto.getStaffDto().getFirstName());
        staff.setLastName(teacherDto.getStaffDto().getLastName());
        staff.setDateOfBirth(teacherDto.getStaffDto().getDateOfBirth());

        schoolClass.setGrade(schoolClassDto.getGrade());
        schoolClass.setLetter(schoolClassDto.getClassLetter());

        teacher.setStaff(staff);
        headMaster.setTeacher(teacher);
        headMaster.setSchoolClass(schoolClass);

        log.debug("Successfully executed add headmaster, {}", headMaster);
        return repository.save(headMaster);
    }

}