package com.hrm.attendancetracking.controller;

import com.hrm.attendancetracking.dto.request.LeaveRequest;
import com.hrm.attendancetracking.dto.response.LeaveResponse;
import com.hrm.attendancetracking.dto.response.ListResponse;
import com.hrm.attendancetracking.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/leaves")
@RequiredArgsConstructor
public class LeaveRequestController {
    private final LeaveRequestService leaveRequestService;

    @PostMapping
    public ResponseEntity<String> leaveRequest(@RequestBody LeaveRequest leaveRequest){
        return new ResponseEntity<>(leaveRequestService.createLeaveRequest(leaveRequest), HttpStatus.CREATED);
    }

    @GetMapping("/eId/{id}")
    public ResponseEntity<ListResponse> getLeaveRequests(@PathVariable Long id){
        return ResponseEntity.ok(leaveRequestService.getLeaveRequests(id));
    }

    @GetMapping("/{leaveId}")
    public ResponseEntity<LeaveResponse> getLeaveById(@PathVariable Integer leaveId){
        return ResponseEntity.ok(leaveRequestService.getLeaveById(leaveId));
    }

    @PatchMapping("/{leaveId}/accept")
    public void acceptLeave(@PathVariable Integer leaveId){
        leaveRequestService.acceptLeave(leaveId);
    }

    @PatchMapping("/{leaveId}/refuse")
    public void refuseLeave(@PathVariable Integer leaveId){
        leaveRequestService.refuseLeave(leaveId);
    }

    @PatchMapping("/{leaveId}/active")
    public void activeLeave(@PathVariable Integer leaveId){
        leaveRequestService.activeLeave(leaveId);
    }

    @PatchMapping("/{leaveId}/inactive")
    public void inactiveLeave(@PathVariable Integer leaveId){
        leaveRequestService.inactiveLeave(leaveId);
    }

    @PutMapping("/{leaveId}")
    public void update(@PathVariable Integer leaveId, @RequestBody LeaveRequest leaveRequest){
        leaveRequestService.update(leaveId, leaveRequest);
    }

    @DeleteMapping("/{leaveId}")
    public void delete(@PathVariable Integer leaveId){
        leaveRequestService.delete(leaveId);
    }
}