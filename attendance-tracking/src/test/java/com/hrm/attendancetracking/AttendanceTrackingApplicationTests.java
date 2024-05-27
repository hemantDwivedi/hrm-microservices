package com.hrm.attendancetracking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrm.attendancetracking.dto.LeaveRequest;
import com.hrm.attendancetracking.repository.AttendanceRepository;
import com.hrm.attendancetracking.repository.LeaveRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AttendanceTrackingApplicationTests {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private AttendanceRepository attendanceRepository;
    @Autowired private LeaveRepository leaveRepository;

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry){
        registry
                .add("spring.data.mysql.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.data.mysql.username",() -> mySQLContainer.getUsername());
        registry.add("spring.data.mysql.password",() -> mySQLContainer.getPassword());
    }

    @Test
    @Order(1)
    void should_create_attendance_record() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/v1/attendances?employeeId=1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

        assertEquals(1, attendanceRepository.findAll().size());
    }

    @Test
    @Order(2)
    void should_get_all_attendances() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/api/v1/attendances")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content()
                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @Order(3)
    void should_updated_out_time() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders.patch("/api/v1/attendances/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

        String result = attendanceRepository.findAll().get(0).getOutTime();
        assertNotEquals(null, result);
    }

    @Test
    @Order(4)
    void should_delete_attendance() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders.delete("/api/v1/attendances/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(0, attendanceRepository.findAll().size());
    }

    @Test
    @Order(5)
    void should_create_leave_request() throws Exception {
        String request = objectMapper.writeValueAsString(getLeaveRequest());
        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/v1/leaves?employeeId=1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                )
                .andDo(print())
                .andExpect(status().isCreated());

        assertEquals(1, leaveRepository.findAll().size());
    }

    @Test
    @Order(6)
    void should_get_all_leave_requests() throws Exception {
        String request = objectMapper.writeValueAsString(getLeaveRequest());
        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/api/v1/leaves")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content()
                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$", hasSize(1)));
    }

    private LeaveRequest getLeaveRequest(){
        return
                LeaveRequest
                        .builder()
                        .startDate("19-06-2024")
                        .endDate("25-06-2024")
                        .leaveType("Casual")
                        .status("pending")
                        .reason("I earned it.")
                        .build();
    }
}
