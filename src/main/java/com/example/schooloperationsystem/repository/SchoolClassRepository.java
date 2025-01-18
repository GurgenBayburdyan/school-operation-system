package com.example.schooloperationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.schooloperationsystem.entity.SchoolClass;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

}