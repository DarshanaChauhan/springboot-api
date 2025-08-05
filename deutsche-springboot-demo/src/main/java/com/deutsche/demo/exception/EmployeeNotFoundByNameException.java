package com.deutsche.demo.exception;

public class EmployeeNotFoundByNameException extends RuntimeException {

    public EmployeeNotFoundByNameException(String name) {
        super("Employee with name " + name + " not found");

    }
}
