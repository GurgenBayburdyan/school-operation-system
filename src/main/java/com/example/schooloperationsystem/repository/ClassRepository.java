package com.example.schooloperationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.schooloperationsystem.entity.Class;

/**
 * @author Gurgen Bayburdyan
 */
public interface ClassRepository extends JpaRepository<Class, Long> {

}
