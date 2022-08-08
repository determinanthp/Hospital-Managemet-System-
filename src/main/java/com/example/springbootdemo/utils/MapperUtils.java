package com.example.springbootdemo.utils;

import com.example.springbootdemo.entity.Patient;
import com.example.springbootdemo.entity.Staff;
import com.example.springbootdemo.model.PatientRequest;
import com.example.springbootdemo.model.PatientResponse;
import com.example.springbootdemo.model.StaffRequest;
import com.example.springbootdemo.model.StaffResponse;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MapperUtils {

    // convert patient to patient response;
    // convert staff to staff response

    public static PatientResponse toPatientResponse(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .name(patient.getName())
                .age(patient.getAge())
                .lastVisitDate(AppUtils.timestampToString(patient.getLastVisitDate()))
                .build();
    }

    public static List<PatientResponse> toPatientResponses(List<Patient> patients) {

        List<PatientResponse> responses = new ArrayList<>();
        for (Patient patient : patients) {
            responses.add(PatientResponse.builder()
                    .id(patient.getId())
                    .name(patient.getName())
                    .age(patient.getAge())
                    .lastVisitDate(AppUtils.timestampToString(patient.getLastVisitDate()))
                    .build());
        }
        return responses;
    }

    public static StaffResponse toStaffResponse(Staff staff) {
        return StaffResponse.builder()
                .id(staff.getId())
                .uuid(staff.getUuid())
                .name(staff.getName())
                .registrationDate(AppUtils.timestampToString(staff.getRegistrationDate()))
                .build();
    }

    // requests

    public static Patient toPatient(PatientRequest patient) {
        return Patient.builder()
                .name(patient.getName())
                .age(patient.getAge())
                .lastVisitDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static Staff toStaff(StaffRequest staff) {
        return Staff.builder()
                .uuid(UUID.randomUUID().toString())
                .name(staff.getName())
                .registrationDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static List<StaffResponse> toStaffResponses(List<Staff> staffs) {
        List<StaffResponse> responses = new ArrayList<>();
        for (Staff staff : staffs) {
            responses.add(StaffResponse.builder()
                    .id(staff.getId())
                    .uuid(staff.getUuid())
                    .name(staff.getName())
                    .registrationDate(AppUtils.timestampToString(staff.getRegistrationDate()))
                    .build());
        }
        return responses;
    }
}

