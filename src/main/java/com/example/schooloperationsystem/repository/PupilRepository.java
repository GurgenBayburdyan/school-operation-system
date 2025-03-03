package com.example.schooloperationsystem.repository;

import com.example.schooloperationsystem.entity.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PupilRepository extends JpaRepository<Pupil, Long> {
    List<Pupil> findAllByDeletedAtIsNull();

    Pupil getByIdAndDeletedAtIsNull(Long id);

    Boolean existsByIdAndDeletedAtIsNull(Long id);

    List<Pupil> findAllBySchool_IdAndDeletedAtIsNull(Long schoolId);
}