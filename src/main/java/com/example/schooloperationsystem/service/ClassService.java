package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.service.params.CreateClassParams;
import com.example.schooloperationsystem.entity.Class;

import java.util.List;

/**
 * @author Gurgen Bayburdyan
 */
public interface ClassService {
    List<Class> getClasses();

    Class addClass(CreateClassParams params);
}
