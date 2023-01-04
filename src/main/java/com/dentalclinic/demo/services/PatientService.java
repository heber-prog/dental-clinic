package com.dentalclinic.demo.services;

import com.dentalclinic.demo.dto.PatientDTO;
import com.dentalclinic.demo.dto.StaffDTO;
import com.dentalclinic.demo.model.Patient;
import com.dentalclinic.demo.model.Staff;
import com.dentalclinic.demo.repository.PatientRepository;
import com.dentalclinic.demo.repository.StaffRepository;
import com.dentalclinic.demo.utils.MapperService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private MapperService mapperService;

    public PatientDTO create(PatientDTO patientDTO) {
        Patient patientEntity = new Patient();
        this.mapperService.mapToEntity(patientDTO, patientEntity);
        Patient savedPatient = this.patientRepository.save(patientEntity);
        patientDTO.setId(savedPatient.getId());
        return patientDTO;
    }

    @Transactional
    public void update(Long id, PatientDTO patientDTO) {
        Patient patientEntity = this.patientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        this.mapperService.mapToEntity(patientDTO, patientEntity);
        this.patientRepository.save(patientEntity);
    }

    public void delete(Long id) {
        this.patientRepository.deleteById(id);
    }

    public List<PatientDTO> list() {
        return this.patientRepository.findAll().stream().map(e -> this.mapperService.mapToDTO(e))
                .collect(Collectors.toList());
    }

    @Transactional
    public StaffDTO getDoctorByPatientId(Long id) {
        Patient patient = this.patientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return this.mapperService.mapToDTO(patient.getDoctor());
    }

    @Transactional
    public void detachDoctorByPatientId(Long id) {
        Patient patient = this.patientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        patient.setDoctor(null);
        this.patientRepository.save(patient);
    }

    @Transactional
    public PatientDTO attachDoctorToPatient(Long patientId, Long doctorId) {
        Patient patient = this.patientRepository.findById(patientId).orElseThrow(EntityNotFoundException::new);
        Staff staff = this.staffRepository.findById(doctorId).orElseThrow(EntityNotFoundException::new);
        patient.setDoctor(staff);
        this.patientRepository.save(patient);
        return this.mapperService.mapToDTO(patient);
    }
}
