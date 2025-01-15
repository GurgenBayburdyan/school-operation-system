package com.example.schooloperationsystem.service;

import com.example.schooloperationsystem.entity.HeadMaster;
import com.example.schooloperationsystem.service.params.CreateHeadMasterParams;

import java.util.List;

public interface HeadMasterService {
    List<HeadMaster> getHeadMasters();
    HeadMaster addHeadMaster(CreateHeadMasterParams params);
}