package com.example.schooloperationsystem.rest.controller;

import com.example.schooloperationsystem.rest.dto.request.CreateStaffRequestDto;
import com.example.schooloperationsystem.rest.dto.response.StaffDetailsDto;
import com.example.schooloperationsystem.rest.facade.StaffFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffFacade staffFacade;

    @GetMapping
    public ResponseEntity<List<StaffDetailsDto>> getAllStaff() {
        return ResponseEntity.ok(staffFacade.getAllStaff());
    }

    @PostMapping
    public ResponseEntity<StaffDetailsDto> create(@RequestBody CreateStaffRequestDto requestDto) {
        return ResponseEntity.ok(staffFacade.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDetailsDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(staffFacade.delete(id));
    }
}