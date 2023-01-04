package com.dentalclinic.demo.repository;

import com.dentalclinic.demo.model.Patient;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends ListCrudRepository<Patient, Long> {
}
