package com.example.schooloperationsystem.rest.facade.impl;

import com.example.schooloperationsystem.rest.dto.response.StatisticsDetailsDto;
import com.example.schooloperationsystem.rest.facade.StatisticsFacade;
import com.example.schooloperationsystem.service.StatisticsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StatisticsFacadeImpl implements StatisticsFacade {
    private final StatisticsService statisticsService;

    @Override
    public StatisticsDetailsDto getStatistics() {
        log.info("Executing get statistics rest API");

        StatisticsDetailsDto statisticsDetailsDto = new StatisticsDetailsDto();

        statisticsDetailsDto.setPupilCount(statisticsService.pupilCount());
        statisticsDetailsDto.setClassCount(statisticsService.classCount());
        statisticsDetailsDto.setStaffCount(statisticsService.staffCount());
        statisticsDetailsDto.setPupilsInClasses(statisticsService.pupilsInClasses());
        statisticsDetailsDto.setMinCountOfPupilsInClass(statisticsService.minCountOfPupilsInClass());
        statisticsDetailsDto.setMaxCountOfPupilsInClass(statisticsService.maxCountOfPupilsInClass());
        statisticsDetailsDto.setSchoolCount(statisticsService.schoolCount());

        log.info("Successfully executed get statistics rest API, response - {}", statisticsDetailsDto);
        return statisticsDetailsDto;
    }
}
