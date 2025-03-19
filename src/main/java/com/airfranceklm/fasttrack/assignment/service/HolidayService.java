package com.airfranceklm.fasttrack.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.airfranceklm.fasttrack.assignment.model.Holiday;
import com.airfranceklm.fasttrack.assignment.repository.HolidayRepository;

/**
 * HolidayService
 */
@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;

    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public List<Holiday> getHolidays() {
        return holidayRepository.findAll();
    }

}
