package com.hrm.attendancetracking.controller;

import com.hrm.attendancetracking.dto.request.AttendanceRequest;
import com.hrm.attendancetracking.dto.response.AttendanceResponse;
import com.hrm.attendancetracking.dto.response.ListResponse;
import com.hrm.attendancetracking.service.ATService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/attendances")
@RequiredArgsConstructor
@Slf4j
public class AttendanceController {
    private final ATService atService;

    @PostMapping
    public ResponseEntity<String> attendanceEntry(@RequestBody AttendanceRequest attendanceRequest){
        log.info("Attendance Entry");
        return new ResponseEntity<>(atService.attendanceEntry(attendanceRequest.getEmployeeId()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListResponse> getAllAttendance(@RequestBody AttendanceRequest attendanceRequest){
        return ResponseEntity.ok(atService.getAllAttendance(attendanceRequest.getEmployeeId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> getAttendanceById(@PathVariable Integer id){
        return ResponseEntity.ok(atService.getAttendanceById(id));
    }

    @PatchMapping("/{id}")
    public void clockOutTime(@PathVariable Integer id) {
        atService.clockOut(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Integer id){
        atService.deleteAttendanceRecord(id);
    }
}