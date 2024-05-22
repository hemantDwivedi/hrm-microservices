package com.hrm.employeeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrm.employeeservice.dto.EmployeeRequest;
import com.hrm.employeeservice.entity.Department;
import com.hrm.employeeservice.repository.DepartmentRepository;
import com.hrm.employeeservice.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceApplicationTests {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private DepartmentRepository departmentRepository;

    @Container
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry){
        registry
                .add("spring.data.mysql.url", mySQLContainer::getJdbcUrl);
    }

    @Test
    @Order(1)
    void should_create_department() throws Exception {
        Department department = getDepartment();
        String departmentStr = objectMapper.writeValueAsString(department);

        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/v1/departments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(departmentStr)
                )
                .andExpect(status().isCreated());

        assertEquals(1, departmentRepository.findAll().size());
    }

    @Test
    @Order(2)
    void should_create_employee() throws Exception {
        EmployeeRequest employee = getEmployee();

        String empString = objectMapper.writeValueAsString(employee);
        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/v1/employees")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(empString)
                )
                .andExpect(status().isCreated());

        assertEquals(1, employeeRepository.findAll().size());
    }

    @Test
    @Order(3)
    void should_get_all_employees() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/api/v1/employees")
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
    @Order(4)
    void should_update_employee() throws Exception {
        EmployeeRequest employee = getUpdatedEmployee();

        String empString = objectMapper.writeValueAsString(employee);
        mockMvc
                .perform(
                        MockMvcRequestBuilders.put("/api/v1/employees/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(empString)
                )
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals("patna", employeeRepository.findAll().get(0).getAddress().getCity());
    }

    @Test
    @Order(5)
    void should_delete_employee() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders.delete("/api/v1/employees/1")
                )
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(0, employeeRepository.findAll().size());
    }

    private Department getDepartment(){
        return new Department(1L, "IT", "IT and software");
    }

    private EmployeeRequest getEmployee(){
        return new EmployeeRequest(
                "hemant",
                "dwiveid",
                "hk1200@gmail.com",
                "2266119900",
                "31-01-1999",
                "male",
                "motihari",
                "bihar",
                "845432",
                "12-04-2020",
                "",
                "backend engineer",
                "7LPA",
                1L
        );
    }

    private EmployeeRequest getUpdatedEmployee(){
        return new EmployeeRequest(
                "hemant",
                "dwiveid",
                "hemant@gmail.com",
                "2266119900",
                "31-01-1999",
                "male",
                "patna",
                "bihar",
                "845432",
                "12-04-2020",
                "",
                "backend engineer",
                "10LPA",
                1L
        );
    }
}
