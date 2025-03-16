package com.example.schooloperationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.schooloperationsystem.entity.SchoolClass;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

    List<SchoolClass> findBySchool_IdAndSchool_DeletedAtIsNull(Long schoolId);

    SchoolClass findBySchoolIdAndGradeAndLetter(Long schoolId, Integer grade, Character letter);
}