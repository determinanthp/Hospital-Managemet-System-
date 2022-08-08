package com.example.springbootdemo.service;

import com.example.springbootdemo.model.StaffRequest;
import com.example.springbootdemo.model.StaffResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffService {
    public StaffResponse createStaff(StaffRequest request);

    public StaffResponse updateStaff(StaffRequest request, Long id);

    public StaffResponse findById(Long id);

    public boolean deleteById(Long id);

    List<StaffResponse> findAllStaff();


}
