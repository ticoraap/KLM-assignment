package com.airfranceklm.fasttrack.assignment.service;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.airfranceklm.fasttrack.assignment.model.Holiday;

/**
 * Checks if the new holiday has a gap of at least 3 working days between
 * holidays.
 *
 * @param newHoliday       The holiday to be scheduled.
 * @param existingHolidays A list of already scheduled holidays.
 * @return {@code true} if the new holiday does not overlap within three days of
 *         any existing holiday, otherwise {@code false}.
 */
@Service
public class HolidayRuleService {

    public boolean hasThreeDaysMargin(Holiday newHoliday, List<Holiday> existingHolidays) {

        // Instant newHolidayEnd = newHoliday.getEndOfHoliday().plus(3,
        // ChronoUnit.DAYS);
        // Instant newHolidayStart = newHoliday.getStartOfHoliday().minus(3,
        // ChronoUnit.DAYS);

        Instant newHolidayEnd = HolidayRuleService.addBusinessDays(newHoliday.getEndOfHoliday(), 3);
        Instant newHolidayStart = HolidayRuleService.subtractBusinessDays(newHoliday.getStartOfHoliday(), 3);

        return existingHolidays.stream()
                .allMatch(existingHoliday -> {

                    Instant existingStart = existingHoliday.getStartOfHoliday();
                    Instant existingEnd = existingHoliday.getEndOfHoliday();

                    return newHolidayEnd.isBefore(existingStart) || newHolidayStart.isAfter(existingEnd);
                });
    }

    private static Instant addBusinessDays(Instant start, int days) {
        LocalDate date = start.atZone(ZoneOffset.UTC).toLocalDate();
        int addedDays = 0;

        while (addedDays < days) {
            date = date.plusDays(1);
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                addedDays++;
            }
        }

        return date.atStartOfDay(ZoneOffset.UTC).toInstant();
    }

    private static Instant subtractBusinessDays(Instant start, int days) {
        LocalDate date = start.atZone(ZoneOffset.UTC).toLocalDate();
        int subtractedDays = 0;

        while (subtractedDays < days) {
            date = date.minusDays(1);
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                subtractedDays++;
            }
        }

        return date.atStartOfDay(ZoneOffset.UTC).toInstant();
    }
}
