package com.cse564.telescope.api;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.closeTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "spring.task.scheduling.enabled=false"
)
@AutoConfigureMockMvc
class TelescopeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void statusEndpointReturnsAggregatedState() throws Exception {
        mockMvc.perform(get("/api/status"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.cyber.clock.rpi").value(10))
            .andExpect(jsonPath("$.cyber.clock.rounds").value(0))
            .andExpect(jsonPath("$.cyber.movementCalculator.idealPos").value(closeTo(0.0, 0.0001)))
            .andExpect(jsonPath("$.cyber.movementMaker.queueSize").value(0))
            .andExpect(jsonPath("$.physical.targetData.rotations.length()", Matchers.greaterThan(0)))
            .andExpect(jsonPath("$.physical.targetData.cyclic").value(true))
            .andExpect(jsonPath("$.physical.telescope.rotation").value(closeTo(0.0, 0.0001)))
            .andExpect(jsonPath("$.sensorsActuators.targetDataSource.round").value(closeTo(0.0, 0.0001)))
            .andExpect(jsonPath("$.sensorsActuators.motor.error").value(closeTo(1.0, 0.0001)));
    }
}
