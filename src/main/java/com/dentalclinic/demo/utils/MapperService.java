package com.dentalclinic.demo.utils;

import com.dentalclinic.demo.dto.PatientDTO;
import com.dentalclinic.demo.dto.StaffDTO;
import com.dentalclinic.demo.model.Patient;
import com.dentalclinic.demo.model.Staff;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    public void mapToEntity(StaffDTO dto, Staff entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setDni(dto.getDni());
        entity.setLicenseNumber(dto.getLicenseNumber());
    }

    public void mapToEntity(PatientDTO dto, Patient entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setDni(dto.getDni());
        entity.setHealthInsuranceCode(dto.getHealthInsuranceCode());
    }

    public StaffDTO mapToDTO(Staff entity) {
        StaffDTO dto = new StaffDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setDni(entity.getDni());
        dto.setLicenseNumber(entity.getLicenseNumber());
        return dto;
    }

    public PatientDTO mapToDTO(Patient entity) {
        PatientDTO dto = new PatientDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setDni(entity.getDni());
        dto.setHealthInsuranceCode(entity.getHealthInsuranceCode());
        if(entity.getDoctor() != null) {
            dto.setDoctorId(entity.getDoctor().getId());
        }
        return dto;
    }
}
