package com.dentalclinic.demo.api;

import com.dentalclinic.demo.dto.PatientDTO;
import com.dentalclinic.demo.dto.StaffDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientStaffControllerTests extends BaseControllerTests {
    private String baseUrl = "/patient";
    
    @Test
    void shouldAttachDoctorToPatient() {
        StaffDTO doctor = createSingleDoctor();

        List<Long> patients = this.createPatients();
        patients.forEach(
                patientId -> {
                    try {
                        this.mockMvc.perform(MockMvcRequestBuilders.put(this.baseUrl + "/" + patientId +
                                                "/doctor/" + doctor.getId())
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)
                                ).andDo(MockMvcResultHandlers.print())
                                .andExpect(status().isOk());
                    } catch (Exception e) {
                        Assertions.fail();
                    }
                }
        );
    }

    @Test
    void shouldDetachDoctorToPatient() {
        StaffDTO doctor = createSingleDoctor();

        List<Long> patients = this.createPatients();
        patients.forEach(id -> {
            patientService.attachDoctorToPatient(id, doctor.getId());
        });

        patients.forEach(
                patientId -> {
                    try {
                        this.mockMvc.perform(MockMvcRequestBuilders.delete(this.baseUrl + "/" + patientId)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)
                                ).andDo(MockMvcResultHandlers.print())
                                .andExpect(status().isOk());
                    } catch (Exception e) {
                        Assertions.fail();
                    }
                }
        );
    }

    @Test
    void shouldReturnDoctorByPatientId() throws Exception {
        StaffDTO doctor = this.createSingleDoctor();
        PatientDTO patient = this.createSinglePatient();
        this.patientService.attachDoctorToPatient(patient.getId(), doctor.getId());

        this.mockMvc.perform(MockMvcRequestBuilders.get(this.baseUrl + "/" + patient.getId()
                                + "/doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}
