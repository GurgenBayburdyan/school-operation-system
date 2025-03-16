package com.example.schooloperationsystem.repository;

import com.example.schooloperationsystem.entity.HeadMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface HeadMasterRepository extends JpaRepository<HeadMaster, Long> {
    Optional<HeadMaster> findByTeacher_Id(Long teacherId);
}
