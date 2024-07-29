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
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class LeaveRequestController {
    private final LeaveRequestService leaveRequestService;

    @PostMapping("/{employeeId}/leaves")
    public ResponseEntity<String> leaveRequest(@RequestBody LeaveRequest leaveRequest, @PathVariable Long employeeId){
        return new ResponseEntity<>(leaveRequestService.createLeaveRequest(leaveRequest, employeeId), HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/leaves")
    public ResponseEntity<List<LeaveResponse>> getLeaveRequests(@PathVariable Long employeeId){
        return ResponseEntity.ok(leaveRequestService.getLeaveRequests(employeeId));
    }

    @GetMapping("/{employeeId}/leaves/{leaveId}")
    public ResponseEntity<LeaveResponse> getLeaveById(@PathVariable Long employeeId, @PathVariable Integer leaveId){
        return ResponseEntity.ok(leaveRequestService.getLeaveById(employeeId, leaveId));
    }

    @PatchMapping("/{employeeId}/leaves/{leaveId}/accept")
    public void acceptLeave(@PathVariable Long employeeId, @PathVariable Integer leaveId){
        leaveRequestService.acceptLeave(employeeId, leaveId);
    }

    @PatchMapping("/{employeeId}/leaves/{leaveId}/refuse")
    public void refuseLeave(@PathVariable Long employeeId, @PathVariable Integer leaveId){
        leaveRequestService.refuseLeave(employeeId, leaveId);
    }

    @PatchMapping("/{employeeId}/leaves/{leaveId}/active")
    public void activeLeave(@PathVariable Long employeeId, @PathVariable Integer leaveId){
        leaveRequestService.activeLeave(employeeId, leaveId);
    }

    @PatchMapping("/{employeeId}/leaves/{leaveId}/inactive")
    public void inactiveLeave(@PathVariable Long employeeId, @PathVariable Integer leaveId){
        leaveRequestService.inactiveLeave(employeeId, leaveId);
    }

    @PutMapping("/{employeeId}/leaves/{leaveId}")
    public void update(@PathVariable Long employeeId, @PathVariable Integer leaveId, @RequestBody LeaveRequest leaveRequest){
        leaveRequestService.update(employeeId, leaveId, leaveRequest);
    }

    @DeleteMapping("/{employeeId}/leaves/{leaveId}")
    public void delete(@PathVariable Long employeeId, @PathVariable Integer leaveId){
        leaveRequestService.delete(employeeId, leaveId);
    }
}