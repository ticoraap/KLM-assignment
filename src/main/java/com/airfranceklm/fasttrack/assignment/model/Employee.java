package com.airfranceklm.fasttrack.assignment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;

/**
 * Employee
 */
@Entity
public class Employee {

    @Id
    @Pattern(regexp = "^klm[0-9]{6}$", message = "Employee ID must follow a pattern 'klm' followed by 6 numbers")
    @Column(unique = true, nullable = false)
    private String employeeId;

    private String name;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
