package com.example.schooloperationsystem.repository;

import com.example.schooloperationsystem.entity.PupilInClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PupilInClassRepository extends JpaRepository<PupilInClass, Long> {

    List<PupilInClass> getBySchoolClassId(Long classId);

    Optional<PupilInClass> findByPupil_Id(Long pupilId);

}