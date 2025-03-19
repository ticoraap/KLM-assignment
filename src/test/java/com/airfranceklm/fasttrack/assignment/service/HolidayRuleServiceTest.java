package com.airfranceklm.fasttrack.assignment.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.airfranceklm.fasttrack.assignment.model.Holiday;

/**
 * HolidayRuleServiceTest
 */
public class HolidayRuleServiceTest {

	@Test
	void testIsValid_BeforeExisting() {

		HolidayRuleService holidayRuleService = new HolidayRuleService();

		Instant existingStart = LocalDateTime.of(2025, 10, 19, 0, 0).toInstant(ZoneOffset.UTC);
		Instant existingEnd = LocalDateTime.of(2025, 10, 23, 0, 0).toInstant(ZoneOffset.UTC);
		Holiday existingHoliday = new Holiday();
		existingHoliday.setStartOfHoliday(existingStart);
		existingHoliday.setEndOfHoliday(existingEnd);

		Instant newStart = LocalDateTime.of(2025, 10, 12, 0, 0).toInstant(ZoneOffset.UTC);
		Instant newEnd = LocalDateTime.of(2025, 10, 15, 23, 59).toInstant(ZoneOffset.UTC);
		Holiday newHoliday = new Holiday();
		newHoliday.setStartOfHoliday(newStart);
		newHoliday.setEndOfHoliday(newEnd);

		List<Holiday> existingHolidays = Arrays.asList(existingHoliday);

		assertTrue(holidayRuleService.hasThreeDaysMargin(newHoliday, existingHolidays));
	}

	@Test
	void testIsFalse_EndsInExisting() {

		HolidayRuleService holidayRuleService = new HolidayRuleService();

		Instant existingStart = LocalDateTime.of(2025, 10, 19, 0, 0).toInstant(ZoneOffset.UTC);
		Instant existingEnd = LocalDateTime.of(2025, 10, 23, 0, 0).toInstant(ZoneOffset.UTC);
		Holiday existingHoliday = new Holiday();
		existingHoliday.setStartOfHoliday(existingStart);
		existingHoliday.setEndOfHoliday(existingEnd);

		Instant newStart = LocalDateTime.of(2025, 10, 12, 0, 0).toInstant(ZoneOffset.UTC);
		Instant newEnd = LocalDateTime.of(2025, 10, 16, 0, 1).toInstant(ZoneOffset.UTC);
		Holiday newHoliday = new Holiday();
		newHoliday.setStartOfHoliday(newStart);
		newHoliday.setEndOfHoliday(newEnd);

		List<Holiday> existingHolidays = Arrays.asList(existingHoliday);

		assertFalse(holidayRuleService.hasThreeDaysMargin(newHoliday, existingHolidays));
	}

	@Test
	void testIsFalse_BeginsAndEndsInExisting() {

		HolidayRuleService holidayRuleService = new HolidayRuleService();

		Instant existingStart = LocalDateTime.of(2025, 10, 19, 0, 0).toInstant(ZoneOffset.UTC);
		Instant existingEnd = LocalDateTime.of(2025, 10, 23, 0, 0).toInstant(ZoneOffset.UTC);
		Holiday existingHoliday = new Holiday();
		existingHoliday.setStartOfHoliday(existingStart);
		existingHoliday.setEndOfHoliday(existingEnd);

		Instant newStart = LocalDateTime.of(2025, 10, 20, 0, 0).toInstant(ZoneOffset.UTC);
		Instant newEnd = LocalDateTime.of(2025, 10, 22, 0, 0).toInstant(ZoneOffset.UTC);
		Holiday newHoliday = new Holiday();
		newHoliday.setStartOfHoliday(newStart);
		newHoliday.setEndOfHoliday(newEnd);

		List<Holiday> existingHolidays = Arrays.asList(existingHoliday);

		assertFalse(holidayRuleService.hasThreeDaysMargin(newHoliday, existingHolidays));
	}

	@Test
	void testIsFalse_BeginsInExisting() {

		HolidayRuleService holidayRuleService = new HolidayRuleService();

		Instant existingStart = LocalDateTime.of(2025, 10, 19, 0, 0).toInstant(ZoneOffset.UTC);
		Instant existingEnd = LocalDateTime.of(2025, 10, 23, 0, 0).toInstant(ZoneOffset.UTC);
		Holiday existingHoliday = new Holiday();
		existingHoliday.setStartOfHoliday(existingStart);
		existingHoliday.setEndOfHoliday(existingEnd);

		Instant newStart = LocalDateTime.of(2025, 10, 26, 0, 0).toInstant(ZoneOffset.UTC);
		Instant newEnd = LocalDateTime.of(2025, 10, 28, 0, 0).toInstant(ZoneOffset.UTC);
		Holiday newHoliday = new Holiday();
		newHoliday.setStartOfHoliday(newStart);
		newHoliday.setEndOfHoliday(newEnd);

		List<Holiday> existingHolidays = Arrays.asList(existingHoliday);

		assertFalse(holidayRuleService.hasThreeDaysMargin(newHoliday, existingHolidays));
	}

	@Test
	void testIsValid_AfterExisting() {

		HolidayRuleService holidayRuleService = new HolidayRuleService();

		// Existing holiday: March 19 - March 23, 2025
		Instant existingStart = LocalDateTime.of(2025, 3, 19, 0, 0).toInstant(ZoneOffset.UTC);
		Instant existingEnd = LocalDateTime.of(2025, 3, 23, 0, 0).toInstant(ZoneOffset.UTC);
		Holiday existingHoliday = new Holiday();
		existingHoliday.setStartOfHoliday(existingStart);
		existingHoliday.setEndOfHoliday(existingEnd);

		// New holiday: March 27 - March 28, 2025 (exactly 3-day gap after existing
		// holiday ends)
		Instant newStart = LocalDateTime.of(2025, 3, 27, 0, 1).toInstant(ZoneOffset.UTC);
		Instant newEnd = LocalDateTime.of(2025, 3, 28, 0, 0).toInstant(ZoneOffset.UTC);
		Holiday newHoliday = new Holiday();
		newHoliday.setStartOfHoliday(newStart);
		newHoliday.setEndOfHoliday(newEnd);

		List<Holiday> existingHolidays = Arrays.asList(existingHoliday);
		assertTrue(holidayRuleService.hasThreeDaysMargin(newHoliday, existingHolidays));
	}
}
