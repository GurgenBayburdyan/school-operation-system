package com.example.schooloperationsystem.repository;

import com.example.schooloperationsystem.entity.Staff;
import com.example.schooloperationsystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    void deleteByStaff(Staff staff);

    void deleteByStaff_Id(Long staffId);

    Teacher findByStaff_Id(Long staffId);

    Teacher findByStaff_IdAndStaff_DeletedAtIsNull(Long staffId);
}