package com.hrm.attendancetracking.controller;

import com.hrm.attendancetracking.model.AttendanceRecord;
import com.hrm.attendancetracking.service.ATService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendances")
@RequiredArgsConstructor
public class AttendanceController {
    private final ATService atService;

    @PostMapping
    public ResponseEntity<String> attendanceEntry(@RequestParam Long employeeId){
        return new ResponseEntity<>(atService.attendanceEntry(employeeId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceRecord>> getAllAttendance(){
        return ResponseEntity.ok(atService.getAllAttendance());
    }

    @GetMapping("/{attendanceId}")
    public ResponseEntity<AttendanceRecord> getAttendanceById(@PathVariable Integer attendanceId){
        return ResponseEntity.ok(atService.getAttendanceById(attendanceId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> recordOutTime(@PathVariable Integer id) throws ParseException {
        return new ResponseEntity<>(atService.recordOutTime(id), HttpStatus.OK);
    }

    @DeleteMapping("/{attendanceId}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Integer attendanceId){
        return ResponseEntity.ok(atService.deleteAttendanceRecord(attendanceId));
    }
}