package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final PupilService pupilService;
    private final StaffService staffService;
    private final SchoolClassService schoolClassService;
    private final PupilInClassService pupilInClassService;
    private final SchoolService schoolService;

    @Override
    @Transactional(readOnly = true)
    public Integer pupilCount() {
        log.debug("Executing get pupil count");

        int count = pupilService.get().size();

        log.debug("Successfully executed get pupil count-{}", count);
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer staffCount() {
        log.debug("Executing get staff count");

        int count = staffService.get().size();

        log.debug("Successfully executed get staff count-{}", count);
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer classCount() {
        log.debug("Executing get class count");

        int count = schoolClassService.get().size();

        log.debug("Successfully executed get class count-{}", count);
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer schoolCount() {
        log.debug("Executing get school count");

        int count = schoolService.get().size();

        log.debug("Successfully executed get school count-{}", count);
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Long, Integer> pupilsInClasses() {
        log.debug("Executing get pupils in classes");

        List<PupilInClass> pupilsInClasses = pupilInClassService.get();
        Map<Long, Integer> map = new HashMap<>();

        for (PupilInClass pupilsInClass : pupilsInClasses) {
            Long classId = pupilsInClass.getSchoolClass().getId();
            if (classId != null) {
                map.put(classId, map.getOrDefault(classId, 0) + 1);
            }
        }

        log.debug("Successfully executed get pupils in classes-{}", map);
        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer maxCountOfPupilsInClass() {
        log.debug("Executing get max pupils count in class");

        Map<Long, Integer> pupilCountMap = pupilsInClasses();

        Integer maxCount = 0;
        for (Integer count : pupilCountMap.values()) {
            if (count > maxCount) {
                maxCount = count;
            }
        }

        log.debug("Successfully executed get max pupils count in class-{}", maxCount);
        return maxCount;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer minCountOfPupilsInClass() {
        log.debug("Executing get min pupils count in class");

        Map<Long, Integer> pupilCountMap = pupilsInClasses();

        if (pupilCountMap.isEmpty()) {
            return 0;
        }
        
        Integer minCount = Integer.MAX_VALUE;
        for (Integer count : pupilCountMap.values()) {
            if (count < minCount) {
                minCount = count;
            }
        }

        log.debug("Successfully executed get min pupils count in class-{}", minCount);
        return minCount;
    }
}