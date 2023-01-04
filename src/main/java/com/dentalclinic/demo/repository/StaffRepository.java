package com.dentalclinic.demo.repository;

import com.dentalclinic.demo.model.Staff;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends ListCrudRepository<Staff, Long> {
}
