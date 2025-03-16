package com.example.schooloperationsystem.repository;

import com.example.schooloperationsystem.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findAllByDeletedAtIsNull();

    List<Staff> findAllBySchool_IdAndDeletedAtIsNull(Long schoolId);
}