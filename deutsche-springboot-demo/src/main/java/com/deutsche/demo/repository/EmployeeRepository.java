//package com.deutsche.demo.repository;
//
//import com.deutsche.demo.model.Employee;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//
//}

package com.deutsche.demo.repository;


import com.deutsche.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//reference -
// https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#jpa.query-methods.query-creation

//	For basic CRUD Operations - no need to write any methods
//	SELECT - findAll();
//	SELECT - findById();
//	INSERT - save();
//	UPDATE - save();
//	DELETE - deleteById();

//	Only for business specific queries, abstract methods need to be declared here.
//	syntax
//	public abstract List<Employee> findByFieldName(String fieldName);
//	example
//	public abstract List<Employee> findByName(String name);
//	public abstract List<Employee> findBySalaryGreaterThan(Double salary);

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//    Optional<Object> getEmployeesByName(String name);

    public abstract  List<Employee> findByName(String name);
    public abstract  List<Employee> findByNameIgnoreCase(String name);
    public abstract  List<Employee> findBySalary(Double salary);
    public abstract  List<Employee> findBySalaryGreaterThan(Double salary);

//    <Employee> findByName(String name);
}