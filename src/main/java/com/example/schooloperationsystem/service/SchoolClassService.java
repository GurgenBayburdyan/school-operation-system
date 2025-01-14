package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.service.params.SchoolCreateClassParams;
import com.example.schooloperationsystem.entity.SchoolClass;

import java.util.List;

/**
 * @author Gurgen Bayburdyan
 */
public interface SchoolClassService {
    List<SchoolClass> getClasses();

    SchoolClass addClass(SchoolCreateClassParams params);
}
