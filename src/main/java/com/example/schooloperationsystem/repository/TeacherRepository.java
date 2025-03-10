package com.example.schooloperationsystem.repository;

import com.example.schooloperationsystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByStaff_IdAndStaff_DeletedAtIsNull(Long staffId);
}