package com.example.springbootdemo.controller;

import com.example.springbootdemo.ValidateStaffService;
import com.example.springbootdemo.entity.Patient;
import com.example.springbootdemo.exceptions.CustomException;
import com.example.springbootdemo.model.PatientRequest;
import com.example.springbootdemo.model.PatientResponse;
import com.example.springbootdemo.service.PatientService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
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
    @GetMapping("/export-patient")
    public void exportCSV(HttpServletResponse response) throws Exception {

        //set file name and content type
        String filename = "users.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<Patient> writer = new StatefulBeanToCsvBuilder<Patient>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all users to csv file
        writer.write(patientService.listPatient());

    }

    @PostMapping
    public ResponseEntity<PatientResponse> saveUser(@RequestHeader(value = "Authorization", required = false) String header, @RequestBody PatientRequest patient) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff) {
        return new ResponseEntity<>(patientService.createPatient(patient), HttpStatus.CREATED);
    }else {
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<PatientResponse> updatePatient(@RequestHeader (value = "Authorization", required = false) String header,
                                                         @PathVariable("id") long id,
                                                         @RequestBody PatientRequest request) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff){
            return  new ResponseEntity<>(patientService.updatePatient(request, id), HttpStatus.OK);
        }else {
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@RequestHeader (value = "Authorization", required = false) String header, @PathVariable("id") long patientId) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff) {
            return new ResponseEntity<Boolean>(patientService.deleteById(patientId), HttpStatus.OK);
        }else {
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> findAllStaff(@RequestHeader (value = "Authorization", required = false) String header,@PathVariable long id) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff){
        return new ResponseEntity<List<PatientResponse>>(patientService.findAllPatient(), HttpStatus.OK);
        }else {
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }


    @DeleteMapping("{date}")
    public ResponseEntity<Boolean> deleteByLastVisitedDateBetween(@RequestHeader (value = "Authorization", required = false) String header, @PathVariable("date") Timestamp date) {
        Boolean validateStaff = validateStaffService.validateStaff(header);
        if (validateStaff){
        return new ResponseEntity<Boolean>(patientService.deleteByLastVisitedDateBetween("startDate", "endDate"), HttpStatus.OK);
    }else {
            throw new CustomException("Invalid user", UNAUTHORIZED);
        }
    }
}
