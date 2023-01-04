package com.dentalclinic.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Staff {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private Long dni;
    private Integer licenseNumber;
}
