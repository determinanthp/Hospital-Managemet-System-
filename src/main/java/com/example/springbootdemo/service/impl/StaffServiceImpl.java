package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.entity.Staff;
import com.example.springbootdemo.model.StaffRequest;
import com.example.springbootdemo.model.StaffResponse;
import com.example.springbootdemo.repository.StaffRepository;
import com.example.springbootdemo.service.StaffService;
import com.example.springbootdemo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public StaffResponse createStaff(StaffRequest request) {
        Staff staff = MapperUtils.toStaff(request);
        staff = staffRepository.save(staff);
        return MapperUtils.toStaffResponse(staff);

    }

    @Override
    public StaffResponse updateStaff(StaffRequest request, Long id) {
        Staff staff = staffRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("staff:" + "id:" + id);
        });
        staff.setName(request.getName());

        staff = staffRepository.save(staff);
        return MapperUtils.toStaffResponse(staff);
    }

    @Override
    public StaffResponse findById(Long id) {
        Staff staff = staffRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("staff with id [" + id + "] does not exist");
        });
        return MapperUtils.toStaffResponse(staff);

    }

    @Override
    public boolean deleteById(Long id) {
        staffRepository.findById(id).map(staff -> {
            staffRepository.deleteById(id);
            return true;
        });
        return false;
    }

    @Override
    public List<StaffResponse> findAllStaff() {
        return MapperUtils.toStaffResponses(staffRepository.findAll());
    }
}
