package com.deutsche.demo.exception;


public class EmployeeNotFoundException extends RuntimeException {

    private EmployeeNotFoundException(String message) {
        super(message);
    }

    public static EmployeeNotFoundException forId(Integer id) {
        return new EmployeeNotFoundException("Employee with id " + id + " not found");
    }

    public static EmployeeNotFoundException forName(String name) {
        return new EmployeeNotFoundException("Employee with name " + name + " not found");
    }

    public static EmployeeNotFoundException forSalary(Double salary) {
        return new EmployeeNotFoundException("Employee with salary " + salary + " not found");
    }

    public static EmployeeNotFoundException forSalaryGreaterThan(Double salary) {
        return new EmployeeNotFoundException("Employee with salary greater than " + salary + " not found");
    }

    public static  EmployeeNotFoundException forNameCaseIgnore(String name) {
        return new EmployeeNotFoundException("Employee with name " + name + " not found");
    }
}
