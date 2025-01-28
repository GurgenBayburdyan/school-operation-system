package com.example.schooloperationsystem.service;


import java.util.Map;

public interface StatisticsService {

    Integer pupilCount();

    Integer staffCount();

    Integer classCount();

    Map<Long, Integer> pupilsInClasses();

    Integer maxCountOfPupilsInClass();

    Integer minCountOfPupilsInClass();

}
