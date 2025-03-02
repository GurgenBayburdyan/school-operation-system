package com.example.schooloperationsystem.service;


import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public interface StatisticsService {

    Integer pupilCount();

    Integer staffCount();

    Integer classCount();

    @Transactional(readOnly = true)
    Integer schoolCount();

    Map<Long, Integer> pupilsInClasses();

    Integer maxCountOfPupilsInClass();

    Integer minCountOfPupilsInClass();

}
