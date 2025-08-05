//package com.deutsche.demo.service;
//
//import com.deutsche.demo.model.Employee;
//import com.deutsche.demo.repository.EmployeeRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EmployeeService {
//
//    EmployeeRepository empRepo;
//    public EmployeeService(EmployeeRepository empRepo) {
//        this.empRepo = empRepo;
//            System.out.println("Repository injected: " + (empRepo != null));
//    }
//
////    private List<Employee> empl = Arrays.asList(
////            new Employee(101, "Sonu", 95000.0),
////            new Employee(102, "Monu", 90000.0),
////            new Employee(103, "Tonu", 85000.0)
////    );
////    public List<Employee> getSampleEmployees() {
////        System.out.println(empl.size());
////        return empl;
////    }
////
////    public Employee getEmployeeById(int id) {
////        System.out.println(id);
////        return empl.stream()
////                .filter(e -> e.getId() == id)
////                .findFirst()
////                .orElse(null);
////    }
//
//    public List<Employee> getAllEmployees() {
//        return empRepo.findAll();
//    }
//
//    public Employee getEmployeeById(Integer id) {
//        Optional<Employee> empOp = empRepo.findById(id);
//        if(empOp.isPresent()) {
//            return empOp.get();
//        }
//        else {
//            throw new RuntimeException(id + " does not exist");
//        }
//    }
//
////    public Employee addEmployee(Employee employee) {
////        return empRepo.save(employee);
////    }
//    public Employee addEmployee(Employee emp) {
//        System.out.println("Received employee: " + emp);  // ✅ Add debug log
//        return empRepo.save(emp); // or whatever your saving method is
//    }
//
//    public void deleteEmployeeById(Integer id) {
////        if (empRepo.existsById(id)) {
////            empRepo.deleteById(id);
////            return true;
////        }
////        return false;
//        if (id == null) {
//            throw new IllegalArgumentException("Employee ID must not be null");
//        }
//
//        // Check if the employee exists
//        if (!empRepo.existsById(id)) {
//            throw new RuntimeException("Cannot delete: Employee with ID " + id + " does not exist");
//        }
//
//        // If all checks pass, delete the employee
//        empRepo.deleteById(id);
//        System.out.println("Deleted employee with ID: " + id);
//    }
//
//
//    public void updateEmployee(Employee updatedEmp) {
//        Employee existingEmp = empRepo.findById(updatedEmp.getId())
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        // Update fields only if new values are provided
//        if (updatedEmp.getName() != null && !updatedEmp.getName().isEmpty()) {
//            existingEmp.setName(updatedEmp.getName());
//        }
//
//        if (updatedEmp.getSalary() != null) {
//            if (updatedEmp.getSalary() <= 0) {
//                throw new IllegalArgumentException("Salary must be greater than 0");
//            }
//            existingEmp.setSalary(updatedEmp.getSalary());
//        }
//
//        empRepo.save(existingEmp);
//    }
//
//}
//
////add, update, delete
//


package com.deutsche.demo.service;

import com.deutsche.demo.exception.EmployeeNotFoundException;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EmployeeRepository employeeRepository;


    public Employee addEmployee(Employee employee) {
//        write your logic
        System.out.println("Received employee: " + employee);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {

        Employee existingEmp = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Update fields only if new values are provided
        if (employee.getName() != null && !employee.getName().isEmpty()) {
            existingEmp.setName(employee.getName());
        }

        if (employee.getSalary() != null) {
            if (employee.getSalary() <= 0) {
                throw new IllegalArgumentException("Salary must be greater than 0");
            }
            existingEmp.setSalary(employee.getSalary());
        }

        employeeRepository.save(existingEmp);
        return employeeRepository.save(existingEmp);
    }

    public List<Employee> getAllEmployees() {
        LOG.info("get all employees");
        return employeeRepository.findAll();
    }

//    public Employee getEmployeeById(Integer id) {
//        LOG.info("Employee with the id " + id);
//        Optional<Employee> employeeOptional = employeeRepository.findById(id);
//        if (employeeOptional.isPresent())
//            return employeeOptional.get();
//        else
//            throw new EmployeeNotFoundException(id);
//    }
//
//    public Employee getEmployeeByName(String name) {
//        LOG.info("Searching employee by name: {}", name);
//        Optional<Employee> employeeOptional = employeeRepository.findByName(name);
//        if (employeeOptional.isPresent())
//            return employeeOptional.get();
//        else
//            throw new EmployeeNotFoundByNameException(name);
//    }
//    public Employee getEmployeeById(Integer id) {
//        LOG.info("Searching for employee with ID: {}", id);
//        Optional<Employee> employeeOptional = employeeRepository.findById(id);
//
//        if (employeeOptional.isPresent()) {
//            LOG.info("Employee found with ID: {}", id);
//            return employeeOptional.get();
//        } else {
//            LOG.error("Employee not found with ID: {}", id);
//            throw new EmployeeNotFoundException.forId(id);
//        }
//    }

    public Employee getEmployeeById(Integer id) {
        LOG.info("Searching for employee with ID: {}", id);
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            LOG.info("Employee found with ID: {}", id);
            return employeeOptional.get();
        } else {
            LOG.error("Employee not found with ID: {}", id);
            throw EmployeeNotFoundException.forId(id);  // ✅ No 'new'
        }
    }


    public List<Employee> getEmployeeByName(String name) {
        LOG.info("Searching for employee with name: {}", name);
        List<Employee> employeeOptional = employeeRepository.findByName(name);

        if (!employeeOptional.isEmpty()) {
            LOG.info("Employees found with name: {}", name);
            return employeeOptional;
        } else {
            LOG.error("Employee not found with name: {}", name);
            throw EmployeeNotFoundException.forName(name);
        }
    }

    public List<Employee> getEmployeeByNameIgnoreCase(String name) {
        LOG.info("Searching for employee with name(case ignorance): {}", name);
        List<Employee> employeeOptional = employeeRepository.findByNameIgnoreCase(name);

        if (!employeeOptional.isEmpty()) {
            LOG.info("Employees found with name(case ignorance): {}", name);
            return employeeOptional;
        } else {
            LOG.error("Employee not found with name (case ignorance): {}", name);
            throw EmployeeNotFoundException.forNameCaseIgnore(name);
        }
    }

    public List<Employee> getEmployeeBySalary(Double salary) {
        LOG.info("Searching for employee with salary: {}", salary);
        List<Employee> employeeOptional = employeeRepository.findBySalary(salary);

        if (!employeeOptional.isEmpty()) {
            LOG.info("Employees found with salary: {}", salary);
            return employeeOptional;
        } else {
            LOG.error("Employee not found with salary: {}", salary);
            throw EmployeeNotFoundException.forSalary(salary);
        }
    }

    public List<Employee> getEmployeeBySalaryGreaterThan(Double salary) {
        LOG.info("Searching for employee with salary Greater than: {}", salary);
        List<Employee> employeeOptional = employeeRepository.findBySalaryGreaterThan(salary);

        if (!employeeOptional.isEmpty()) {
            LOG.info("Employees found with salary greter than: {}", salary);
            return employeeOptional;
        } else {
            LOG.error("Employee not found with salary greater than: {}", salary);
            throw EmployeeNotFoundException.forSalaryGreaterThan(salary);
        }
    }


    public Employee deleteEmployee(Integer id) {


        if (id == null) {
            throw new IllegalArgumentException("Employee ID must not be null");
        }

        // Check if the employee exists
//        if (!employeeRepository.existsById(id)) {
//            throw new RuntimeException("Cannot delete: Employee with ID " + id + " does not exist");
//        }
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found"));


        // If all checks pass, delete the employee
        LOG.info("Deleting employee: {}", existingEmployee);
        employeeRepository.deleteById(id);
        return null;
    }

}