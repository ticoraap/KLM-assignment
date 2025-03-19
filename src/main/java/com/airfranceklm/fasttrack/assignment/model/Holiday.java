package com.airfranceklm.fasttrack.assignment.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Holiday
 */
@Entity
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String holidayId;

    private String holidayLabel;

    private String employeeId;

    private Instant startOfHoliday;

    private Instant endOfHoliday;

    private Status status;

    public String getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(String holidayId) {
        this.holidayId = holidayId;
    }

    public String getHolidayLabel() {
        return holidayLabel;
    }

    public void setHolidayLabel(String holidayLabel) {
        this.holidayLabel = holidayLabel;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Instant getStartOfHoliday() {
        return startOfHoliday;
    }

    public void setStartOfHoliday(Instant startOfHoliday) {
        this.startOfHoliday = startOfHoliday;
    }

    public Instant getEndOfHoliday() {
        return endOfHoliday;
    }

    public void setEndOfHoliday(Instant endOfHoliday) {
        this.endOfHoliday = endOfHoliday;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
