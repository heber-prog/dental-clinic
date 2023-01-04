package com.dentalclinic.demo.dto;

import lombok.Data;

@Data
public class PatientDTO extends PersonDTO {
    private String healthInsuranceCode;
    private Long doctorId;
}
