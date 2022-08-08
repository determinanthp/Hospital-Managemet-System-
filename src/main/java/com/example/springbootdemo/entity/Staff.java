package com.example.springbootdemo.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "staff")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "uuid", length = 20)
    private String uuid;

    @Column(name = "registration_date")
    private Timestamp registrationDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = ALL)
    @JoinTable(name = "staff_patients", joinColumns =
    @JoinColumn(name = "staff_id", referencedColumnName = "id"), inverseJoinColumns =
    @JoinColumn(name = "patient_id", referencedColumnName = "id"))
    private Set<Patient> patients;

}
