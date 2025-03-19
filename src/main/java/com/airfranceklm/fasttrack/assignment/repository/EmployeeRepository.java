package com.airfranceklm.fasttrack.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airfranceklm.fasttrack.assignment.model.Employee;

/**
 * EmployeeRepository
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
