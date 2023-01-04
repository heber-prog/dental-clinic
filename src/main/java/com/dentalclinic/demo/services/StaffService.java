package com.dentalclinic.demo.services;

import com.dentalclinic.demo.dto.StaffDTO;
import com.dentalclinic.demo.model.Staff;
import com.dentalclinic.demo.repository.StaffRepository;
import com.dentalclinic.demo.utils.MapperService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffService {
    @Autowired
    private StaffRepository repository;
    @Autowired
    private MapperService mapperService;


    public StaffDTO create(StaffDTO patient) {
        Staff staffEntity = new Staff();
        this.mapperService.mapToEntity(patient, staffEntity);
        patient.setId(this.repository.save(staffEntity).getId());
        return patient;
    }

    @Transactional
    public void update(Long id, StaffDTO patient) {
        Staff staffEntity = this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
        this.mapperService.mapToEntity(patient, staffEntity);
        this.repository.save(staffEntity);
    }

    public void delete(Long id) {
        this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
        this.repository.deleteById(id);
    }

    public List<StaffDTO> list() {
        return this.repository.findAll().stream().map(e -> this.mapperService.mapToDTO(e)).collect(Collectors.toList());
    }

    public StaffDTO getById(Long id) {
        return this.mapperService.mapToDTO(this.repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
