package com.dentalclinic.demo.api;

import com.dentalclinic.demo.dto.PatientDTO;
import com.dentalclinic.demo.services.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public PatientDTO create(@Valid @RequestBody @NotNull PatientDTO patient) {
        return this.patientService.create(patient);
    }

    @PutMapping("/{id}")
    public void update(@Valid @PathVariable @NotNull Long id, @Valid @RequestBody @NotNull PatientDTO patient) {
        this.patientService.update(id, patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @PathVariable @NotNull Long id) {
        this.patientService.delete(id);
    }

    @GetMapping
    public void list() {
        this.patientService.list();
    }
}
