package com.example.springbootdemo.repository;

import com.example.springbootdemo.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findPatientsByLastVisitDateBetween(Timestamp startDate, Timestamp endDate);
}
