package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.Patient;
import com.example.springbootdemo.model.PatientRequest;
import com.example.springbootdemo.model.PatientResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {

    public PatientResponse createPatient(PatientRequest request);

    public PatientResponse updatePatient(PatientRequest request, Long id);

    public boolean deleteById(Long id);

    public boolean deleteByLastVisitedDateBetween(String startDate, String endDate);

    List<PatientResponse> findAllPatient();

    List <Patient> listPatient();

    List<Patient> downloadPatientDetails(Long id);
}
