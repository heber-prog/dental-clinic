package com.dentalclinic.demo.dto;

import lombok.Data;

@Data
public class StaffDTO extends PersonDTO {
    private Integer licenseNumber;
}
