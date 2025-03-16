package com.example.schooloperationsystem.repository;

import com.example.schooloperationsystem.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findAllByDeletedAtIsNull();

    Optional<School> findByIdAndDeletedAtIsNull(Long id);
}
