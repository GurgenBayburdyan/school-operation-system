package com.example.schooloperationsystem.repository;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadMasterRepository extends JpaRepository<HeadMaster, Long> {
    void deleteByTeacher(Teacher teacher);
}
