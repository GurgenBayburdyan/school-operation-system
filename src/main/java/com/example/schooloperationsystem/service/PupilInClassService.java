package com.example.schooloperationsystem.service;
import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.service.params.CreatePupilInClassParams;
import java.util.List;
/**
 * @author Gurgen Bayburdyan
 */
public interface PupilInClassService {
    List<PupilInClass> getPupilInClasses();
    PupilInClass addPupil(CreatePupilInClassParams params);
}