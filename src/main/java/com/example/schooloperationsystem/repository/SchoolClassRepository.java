package com.example.schooloperationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.schooloperationsystem.entity.SchoolClass;

/**
 * @author Gurgen Bayburdyan
 */
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

}
