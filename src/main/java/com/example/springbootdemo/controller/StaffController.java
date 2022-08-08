package com.example.springbootdemo.controller;

import com.example.springbootdemo.ValidateStaffService;
import com.example.springbootdemo.exceptions.CustomException;
import com.example.springbootdemo.model.StaffRequest;
import com.example.springbootdemo.model.StaffResponse;
import com.example.springbootdemo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private ValidateStaffService validateStaffService;

    @PostMapping
    public ResponseEntity<StaffResponse> saveUser(@RequestHeader(value = "Authorization", required = false) String header, @RequestBody StaffRequest staff) {
        // call validate method here first
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff) {
            return new ResponseEntity<>(staffService.createStaff(staff), CREATED);
        } else {
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updateStaff(@RequestHeader(value = "Authorization", required = false) String header,
                                      @RequestBody StaffRequest staff,
                                      @PathVariable Long id) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff){
            return new ResponseEntity<>(staffService.updateStaff(staff, id), HttpStatus.OK);
        } else {
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<StaffResponse> findStaffById(@RequestHeader(value = "Authorization", required = false) String header, @PathVariable("id") long staffId) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff){
            return new ResponseEntity<>(staffService.findById(staffId), OK);
        }else{
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<List<StaffResponse>> findAllStaff(@RequestHeader(value = "Authorization", required = false) String header) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff){
            return new ResponseEntity<>(staffService.findAllStaff(), OK);
        }else {
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteStaff(@RequestHeader(value = "Authorization", required = false) String header, @PathVariable("id") long id) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff) {
            return new ResponseEntity<>(staffService.deleteById(id), OK);
        }else {
            throw new CustomException("invalid user", UNAUTHORIZED);
        }
    }
}
