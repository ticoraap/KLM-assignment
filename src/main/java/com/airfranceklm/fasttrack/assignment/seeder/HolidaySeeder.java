package com.airfranceklm.fasttrack.assignment.seeder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.airfranceklm.fasttrack.assignment.model.Holiday;
import com.airfranceklm.fasttrack.assignment.model.Status;
import com.airfranceklm.fasttrack.assignment.repository.HolidayRepository;

/**
 * HolidaySeeder
 */
@Component
public class HolidaySeeder implements CommandLineRunner {

    @Autowired
    private HolidayRepository holidayRepository;

    @Override
    public void run(String... args) {
        Holiday holiday = new Holiday();
        holiday.setHolidayLabel("Test holiday");
        holiday.setStatus(Status.REQUESTED);
        holiday.setStartOfHoliday(Instant.now());
        holiday.setEndOfHoliday(Instant.now().plus(1, ChronoUnit.DAYS));
        holiday.setEmployeeId("KLM012345");

        holidayRepository.save(holiday);
    }
}
