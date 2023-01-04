package com.dentalclinic.demo.api;

import com.dentalclinic.demo.dto.PatientDTO;
import com.dentalclinic.demo.dto.StaffDTO;
import com.dentalclinic.demo.services.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientStaffController {
    @Autowired
    private PatientService patientService;

    @PutMapping("/{patientId}/doctor/{doctorId}")
    public PatientDTO attachDoctorToPatient(@Valid @PathVariable @NotNull Long patientId, @Valid @PathVariable @NotNull Long doctorId) {
        return this.patientService.attachDoctorToPatient(patientId, doctorId);
    }

    @GetMapping("/{id}/doctor")
    public StaffDTO getDoctorByPatientId(@Valid @PathVariable @NotNull Long id) {
        return this.patientService.getDoctorByPatientId(id);
    }

    @DeleteMapping("/{id}/doctor")
    public void deleteDoctorByPatientId(@Valid @PathVariable @NotNull Long id) {
        this.patientService.detachDoctorByPatientId(id);
    }
}
