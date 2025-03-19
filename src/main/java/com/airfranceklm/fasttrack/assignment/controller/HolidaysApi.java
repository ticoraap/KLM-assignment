package com.airfranceklm.fasttrack.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airfranceklm.fasttrack.assignment.model.Holiday;
import com.airfranceklm.fasttrack.assignment.service.HolidayService;

@Controller
@RequestMapping("/holidays")
public class HolidaysApi {

    @Autowired
    private HolidayService holidayService;

    @GetMapping
    public ResponseEntity<List<Holiday>> getHolidays() {
        return ResponseEntity.ok(holidayService.getHolidays());
    }

    @PostMapping
    public ResponseEntity<Holiday> addHoliday(@RequestBody Holiday holiday) {
        return ResponseEntity.ok(holidayService.addHoliday(holiday));
    }
}
