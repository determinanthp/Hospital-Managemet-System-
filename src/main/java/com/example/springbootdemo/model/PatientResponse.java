package com.example.springbootdemo.model;

import lombok.*;

import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PatientResponse {
    @Id
    private long id;
    private String name;
    private Integer age;
    private String lastVisitDate;
}
