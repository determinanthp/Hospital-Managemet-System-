package com.example.springbootdemo.model;

import lombok.*;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffResponse {
    @Id
    private Long id;
    private String name;
    private String uuid;
    private String registrationDate;
}
