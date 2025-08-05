package com.deutsche.demo.model;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "employees")
public class Employee {


//    List<Employee> employees= new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private Integer id;

    @NotNull(message = "Salary cannot be null")
    @NotBlank(message = "Salary cannot be blank")
    @Size(min=2, max=50,message = "Name should be in proper form")
    @Column(name="name")
    private String name;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be greater than zero")
    @Column(name="salary")
    private Double salary;

    public Employee() {
//        this.id = id;
//        this.name = name;
//        this.salary = salary;
    }


// Getters
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", salary=" + salary + '}';
    }


}
