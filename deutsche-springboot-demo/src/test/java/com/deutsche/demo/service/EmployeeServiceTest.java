package com.deutsche.demo.service;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import com.deutsche.demo.exception.EmployeeNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    public EmployeeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEmployeeById_Found() {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("Monu");

        when(repository.findById(1)).thenReturn(Optional.of(emp));
        Employee result = service.getEmployeeById(1);

        assertEquals("Monu", result.getName());
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> service.getEmployeeById(99));
    }

//    @Test
//    void testGetEmployeesByName() {
//        Employee e1 = new Employee();
//        e1.setName("Monu");
//
//        when(repository.findAllByNameIgnoreCase("monu")).thenReturn(List.of(e1));
//        List<Employee> results = service.getEmployeesByName("monu");
//
//        assertEquals(1, results.size());
//    }
}
