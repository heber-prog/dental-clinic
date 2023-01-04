package com.dentalclinic.demo.api;

import com.dentalclinic.demo.dto.PatientDTO;
import com.dentalclinic.demo.dto.StaffDTO;
import com.dentalclinic.demo.services.PatientService;
import com.dentalclinic.demo.services.StaffService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BaseControllerTests {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected StaffService staffService;
    @Autowired
    protected PatientService patientService;

    protected ObjectMapper mapper = new ObjectMapper();

    // Devuelve los IDs del staff creado recientemente
    protected List<Long> createPatients() {
        PatientDTO body1 = new PatientDTO();
        body1.setFirstName("Patricio");
        body1.setLastName("Villa");
        body1.setDni(39878654L);
        body1.setHealthInsuranceCode("000012344567");

        PatientDTO body2 = new PatientDTO();
        body2.setFirstName("Amalia");
        body2.setLastName("Cortez");
        body2.setDni(41232114L);
        body2.setHealthInsuranceCode("B1200000076");

        PatientDTO body3 = new PatientDTO();
        body3.setFirstName("Patricio");
        body3.setLastName("Chavez");
        body3.setDni(376546373L);
        body3.setHealthInsuranceCode("C1500000022");

        return Stream.of(body1, body2, body3).map(
                b -> this.patientService.create(b).getId()
        ).collect(Collectors.toList());
    }

    // Devuelve los IDs del staff creado recientemente
    protected List<Long> createStaff() {
        StaffDTO body1 = new StaffDTO();
        body1.setFirstName("Carmela");
        body1.setLastName("Montoya");
        body1.setDni(99222333L);
        body1.setLicenseNumber(1661);

        StaffDTO body2 = new StaffDTO();
        body2.setFirstName("Miguel");
        body2.setLastName("Alvarez");
        body2.setDni(223457657L);
        body2.setLicenseNumber(1950);

        StaffDTO body3 = new StaffDTO();
        body3.setFirstName("Angela");
        body3.setLastName("Bernave");
        body3.setDni(426543322L);
        body3.setLicenseNumber(1522);

        return Stream.of(body1, body2, body3).map(
                b -> this.staffService.create(b).getId()
        ).collect(Collectors.toList());
    }

    protected StaffDTO createSingleDoctor() {
        StaffDTO doctor = new StaffDTO();
        doctor.setFirstName("German");
        doctor.setLastName("Remotti");
        doctor.setDni(16535673L);
        doctor.setLicenseNumber(1432);
        this.staffService.create(doctor);
        return doctor;
    }

    protected PatientDTO createSinglePatient() {
        PatientDTO body = new PatientDTO();
        body.setFirstName("German");
        body.setLastName("Remotti");
        body.setDni(16535673L);
        body.setHealthInsuranceCode("G1500002933");
        this.patientService.create(body);
        return body;
    }
}
