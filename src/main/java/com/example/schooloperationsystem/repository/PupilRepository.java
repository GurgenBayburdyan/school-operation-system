package com.example.schooloperationsystem.repository;

import com.example.schooloperationsystem.entity.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PupilRepository extends JpaRepository<Pupil, Long> {
}
