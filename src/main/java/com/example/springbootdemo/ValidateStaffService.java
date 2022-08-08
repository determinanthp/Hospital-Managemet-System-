package com.example.springbootdemo;

import com.example.springbootdemo.entity.Staff;
import com.example.springbootdemo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateStaffService {

    @Autowired
    private StaffRepository staffRepository;

    public Boolean validateStaff(String uuid) {
        Staff staff = staffRepository.findStaffByUuid(uuid);
        if (staff == null) {
            return false;
        } else {
            return true;
        }
    }
}
