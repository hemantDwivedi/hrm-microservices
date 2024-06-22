package com.hrm.attendancetracking.controller;

import com.hrm.attendancetracking.dto.LeaveRequest;
import com.hrm.attendancetracking.dto.LeaveResponse;
import com.hrm.attendancetracking.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leaves")
@RequiredArgsConstructor
public class LeaveRequestController {
    private final LeaveRequestService leaveRequestService;

    @PostMapping
    public ResponseEntity<String> leaveRequest(@RequestBody LeaveRequest leaveRequest, @RequestParam Long employeeId){
        return new ResponseEntity<>(leaveRequestService.createLeaveRequest(leaveRequest, employeeId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getLeaveRequests(){
        return ResponseEntity.ok(leaveRequestService.getLeaveRequests());
    }

    @GetMapping("/{leaveId}")
    public ResponseEntity<LeaveResponse> getLeaveById(@PathVariable Integer leaveId){
        return ResponseEntity.ok(leaveRequestService.getLeaveById(leaveId));
    }
}