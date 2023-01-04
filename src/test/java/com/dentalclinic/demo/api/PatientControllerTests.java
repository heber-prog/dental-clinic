package com.dentalclinic.demo.api;

import com.dentalclinic.demo.dto.PatientDTO;
import com.dentalclinic.demo.dto.StaffDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientControllerTests extends BaseControllerTests {
    private String baseUrl = "/patient";

    @Test
    void shouldCreatePatient() throws Exception {
        PatientDTO body = this.createSinglePatient();

        this.mockMvc.perform(MockMvcRequestBuilders.post(this.baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdatePatient() throws Exception {
        PatientDTO body = createSinglePatient();
        this.mockMvc.perform(MockMvcRequestBuilders.put(this.baseUrl + "/" + body.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteStaff() throws Exception {
        List<Long> staffIds = this.createPatients();
        staffIds.forEach(id -> {
            try {
                this.mockMvc.perform(MockMvcRequestBuilders.delete(this.baseUrl + "/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk());
            } catch (Exception e) {
                Assertions.fail();
            }
        });
    }

    @Test
    void shouldListStaff() throws Exception {
        this.createPatients();
        this.mockMvc.perform(MockMvcRequestBuilders.get(this.baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}
