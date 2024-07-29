package com.hrm.attendancetracking.controller;

import com.hrm.attendancetracking.dto.AttendanceResponse;
import com.hrm.attendancetracking.service.ATService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class AttendanceController {
    private final ATService atService;

    @PostMapping("/{employeeId}/attendances")
    public ResponseEntity<String> attendanceEntry(@PathVariable Long employeeId){
        return new ResponseEntity<>(atService.attendanceEntry(employeeId), HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/attendances")
    public ResponseEntity<List<AttendanceResponse>> getAllAttendance(@PathVariable Long employeeId){
        return ResponseEntity.ok(atService.getAllAttendance(employeeId));
    }

    @GetMapping("/{employeeId}/attendances/{attendanceId}")
    public ResponseEntity<AttendanceResponse> getAttendanceById(@PathVariable Long employeeId, @PathVariable Integer attendanceId){
        return ResponseEntity.ok(atService.getAttendanceById(employeeId, attendanceId));
    }

    @PatchMapping("/{employeeId}/attendances/{id}")
    public void clockOutTime(@PathVariable Long employeeId, @PathVariable Integer id) {
        atService.clockOut(employeeId, id);
    }

    @DeleteMapping("/{employeeId}/attendances/{attendanceId}")
    public void deleteAttendance(@PathVariable Long employeeId, @PathVariable Integer attendanceId){
        atService.deleteAttendanceRecord(employeeId, attendanceId);
    }
}