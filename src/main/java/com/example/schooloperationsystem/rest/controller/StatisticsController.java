package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.dto.response.StatisticsDetailsDto;
import com.example.schooloperationsystem.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<StatisticsDetailsDto> getStatistics() {
        log.info("Executing get statistics rest API");

        StatisticsDetailsDto statisticsDetailsDto = new StatisticsDetailsDto();

        statisticsDetailsDto.setPupilCount(statisticsService.pupilCount());
        statisticsDetailsDto.setClassCount(statisticsService.classCount());
        statisticsDetailsDto.setStaffCount(statisticsService.staffCount());
        statisticsDetailsDto.setPupilsInClasses(statisticsService.pupilsInClasses());
        statisticsDetailsDto.setMinCountOfPupilsInClass(statisticsService.minCountOfPupilsInClass());
        statisticsDetailsDto.setMaxCountOfPupilsInClass(statisticsService.maxCountOfPupilsInClass());

        ResponseEntity<StatisticsDetailsDto> responseEntity = ResponseEntity.ok(statisticsDetailsDto);

        log.info("Successfully executed get statistics rest API, response entity - {}", responseEntity);
        return responseEntity;
    }
}