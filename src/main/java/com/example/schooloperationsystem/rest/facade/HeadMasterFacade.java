package com.example.schooloperationsystem.rest.facade;

import com.example.schooloperationsystem.rest.dto.request.CreateHeadMasterRequestDto;
import com.example.schooloperationsystem.rest.dto.response.HeadMasterDetailsDto;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

public interface HeadMasterFacade {
    List<HeadMasterDetailsDto> getAll();

    HeadMasterDetailsDto create(@RequestBody CreateHeadMasterRequestDto requestDto);

    HeadMasterDetailsDto getByTeacherId(Long teacherId);
}
