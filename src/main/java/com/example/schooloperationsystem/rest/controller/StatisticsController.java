package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.dto.response.StatisticsDetailsDto;
import com.example.schooloperationsystem.rest.facade.StatisticsFacade;
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

    private final StatisticsFacade statisticsFacade;

    @GetMapping
    public ResponseEntity<StatisticsDetailsDto> getStatistics() {
        return ResponseEntity.ok(statisticsFacade.getStatistics());
    }
}