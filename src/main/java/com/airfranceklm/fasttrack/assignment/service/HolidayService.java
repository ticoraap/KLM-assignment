package com.airfranceklm.fasttrack.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airfranceklm.fasttrack.assignment.model.Holiday;
import com.airfranceklm.fasttrack.assignment.repository.HolidayRepository;

import jakarta.transaction.Transactional;

/**
 * HolidayService
 */
@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private HolidayRuleService holidayRuleService;

    public List<Holiday> getHolidays() {
        return holidayRepository.findAll();
    }

    @Transactional
    public Holiday addHoliday(Holiday newHoliday) throws IllegalArgumentException {

        List<Holiday> holidays = holidayRepository.findAll();

        if (!holidayRuleService.hasThreeDaysMargin(newHoliday, holidays)) {
            throw new IllegalArgumentException("Holiday must meet the scheduling rules");
        }

        return holidayRepository.save(newHoliday);
    }
}
