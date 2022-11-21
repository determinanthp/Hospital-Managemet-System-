package com.example.springbootdemo.controller;

import com.example.springbootdemo.ValidateStaffService;
import com.example.springbootdemo.exceptions.CustomException;
import com.example.springbootdemo.model.PatientRequest;
import com.example.springbootdemo.model.PatientResponse;
import com.example.springbootdemo.service.PatientService;
import com.example.springbootdemo.utils.ExportUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;
    private final ValidateStaffService validateStaffService;

    public PatientController(PatientService patientService, ValidateStaffService validateStaffService) {
        this.patientService = patientService;
        this.validateStaffService = validateStaffService;
    }

    @GetMapping("/export-patient/{id}")
    public void exportCSV(@PathVariable("id") Long id, HttpServletResponse response) {
        ExportUtils.exportCSV(response, patientService.downloadPatientDetails(id), "patient");
    }

    @PostMapping
    public ResponseEntity<PatientResponse> saveUser(@RequestHeader(value = "Authorization", required = false) String header,
                                                    @RequestBody PatientRequest patient) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff) {
            return new ResponseEntity<>(patientService.createPatient(patient), HttpStatus.CREATED);
        } else {
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<PatientResponse> updatePatient(@RequestHeader(value = "Authorization", required = false) String header,
                                                         @PathVariable("id") long id,
                                                         @RequestBody PatientRequest request) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff) {
            return new ResponseEntity<>(patientService.updatePatient(request, id), HttpStatus.OK);
        } else {
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@RequestHeader(value = "Authorization", required = false) String header,
                                              @PathVariable("id") long patientId) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff) {
            return new ResponseEntity<>(patientService.deleteById(patientId), HttpStatus.OK);
        } else {
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> findAllStaff(@RequestHeader(value = "Authorization", required = false) String header) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff) {
            return new ResponseEntity<>(patientService.findAllPatient(), HttpStatus.OK);
        } else {
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }


    @DeleteMapping
    public ResponseEntity<Boolean> deleteByLastVisitedDateBetween(@RequestHeader(value = "Authorization", required = false) String header,
                                                                  @RequestParam String startDate,
                                                                  @RequestParam String endDate) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff) {
            return new ResponseEntity<>(patientService.deleteByLastVisitedDateBetween(startDate, endDate), HttpStatus.OK);
        } else {
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }
}
