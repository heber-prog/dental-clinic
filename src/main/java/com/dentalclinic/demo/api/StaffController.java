package com.dentalclinic.demo.api;

import com.dentalclinic.demo.dto.StaffDTO;
import com.dentalclinic.demo.services.StaffService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping
    public StaffDTO create(@Valid @RequestBody @NotNull StaffDTO patient) {
        return this.staffService.create(patient);
    }

    @PutMapping("/{id}")
    public void update(@Valid @PathVariable @NotNull Long id, @Valid @RequestBody @NotNull StaffDTO patient) {
        this.staffService.update(id, patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @PathVariable @NotNull Long id) {
        this.staffService.delete(id);
    }

    @GetMapping
    public List<StaffDTO> list() {
        return this.staffService.list();
    }
}
