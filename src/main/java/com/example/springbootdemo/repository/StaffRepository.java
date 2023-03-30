package com.example.springbootdemo.repository;

import com.example.springbootdemo.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    Staff findStaffByUuid(String uuid);

    List<String> stream();
}
