package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.entity.Patient;
import com.example.springbootdemo.model.PatientRequest;
import com.example.springbootdemo.model.PatientResponse;
import com.example.springbootdemo.repository.PatientRepository;
import com.example.springbootdemo.service.PatientService;
import com.example.springbootdemo.utils.AppUtils;
import com.example.springbootdemo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientResponse createPatient(PatientRequest request) {
        Patient patient = MapperUtils.toPatient(request);
        patient = patientRepository.save(patient);
        return MapperUtils.toPatientResponse(patient);

    }

    @Override
    public PatientResponse updatePatient(PatientRequest request, Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("patient:" + "id:" + id);
        });
        if (request.getName() != null) {
            patient.setName(request.getName());
        }

        if (request.getAge() != null) {
            patient.setAge(request.getAge());
        }
        patient = patientRepository.save(patient);
        return MapperUtils.toPatientResponse(patient);
    }

    @Override
    public boolean deleteById(Long id) {
        patientRepository.findById(id).map(patient -> {
            patientRepository.delete(patient);
            return true;
        });
        return false;
    }

    @Override
    public boolean deleteByLastVisitedDateBetween(String startDate, String endDate) {

        List<Patient> patient = patientRepository
                .findPatientsByLastVisitDateBetween(AppUtils.stringToTimestamp(startDate), AppUtils.stringToTimestamp(endDate));
        if (patient.isEmpty()) {
            System.out.println("Patient is empty");
            return false;
        }

        patientRepository.deleteAll(patient);
        return true;
    }

    @Override
    public List<PatientResponse> findAllPatient() {
        return MapperUtils.toPatientResponses(patientRepository.findAll());
    }

    @Override
    public List<Patient> listPatient() {
        return null;
    }
}
