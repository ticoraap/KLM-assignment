package com.airfranceklm.fasttrack.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airfranceklm.fasttrack.assignment.model.Holiday;

/**
 * HolidayRepository
 */
public interface HolidayRepository extends JpaRepository<Holiday, String> {
    List<Holiday> findByEmployeeId(String employeeId);
}
