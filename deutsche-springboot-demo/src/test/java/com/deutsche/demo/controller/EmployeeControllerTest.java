package com.deutsche.demo.controller;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.junit.jupiter.api.BeforeEach;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEmployee() {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("Alice");
        emp.setSalary(60000.0);

        when(employeeService.addEmployee(emp)).thenReturn(emp);

        Employee result = employeeController.addEmployee(emp);
        assertEquals("Alice", result.getName());
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> list = Arrays.asList(
                new Employee() {{ setId(1); setName("John"); setSalary(45000.0); }},
                new Employee() {{ setId(2); setName("Jane"); setSalary(50000.0); }}
        );

        when(employeeService.getAllEmployees()).thenReturn(list);

        List<Employee> result = employeeController.getAllEmployees();
        assertEquals(2, result.size());
        verify(employeeService, times(1)).getAllEmployees();
    }
    @Test
    void testGetEmployeeById() {
        Employee emp = new Employee();
        emp.setId(13);
        emp.setName("Steve");
        emp.setSalary(75000.0);

        when(employeeService.getEmployeeById(3)).thenReturn(emp);

        ResponseEntity<Employee> response = employeeController.getEmployeeById(13); // updated
        Employee result = response.getBody(); // unwrap

        assertNotNull(result);
        assertEquals("steve", result.getName());
    }

}
