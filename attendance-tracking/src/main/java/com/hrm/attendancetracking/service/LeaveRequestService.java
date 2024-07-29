package com.hrm.attendancetracking.service;

import com.hrm.attendancetracking.client.ApiClient;
import com.hrm.attendancetracking.dto.LeaveRequest;
import com.hrm.attendancetracking.dto.LeaveResponse;
import com.hrm.attendancetracking.exception.ResourceNotFoundException;
import com.hrm.attendancetracking.model.Leave;
import com.hrm.attendancetracking.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {
    private final LeaveRepository leaveRepository;
    private final ApiClient apiClient;

    public String createLeaveRequest(LeaveRequest leaveRequest, Long employeeId) {
        Boolean employee = apiClient.existById(employeeId);
        if (!employee) throw new ResourceNotFoundException("Employee not found");
        Leave leave = mapToLeave(leaveRequest);
        leave.setEmployeeId(employeeId);
        Leave leaveCreated = leaveRepository.save(leave);
        return "Leave request submitted ID: " + leaveCreated.getId();
    }

    public List<LeaveResponse> getLeaveRequests(Long id) {
        return
                leaveRepository
                        .findByEmployeeId(id)
                        .stream().map(
                                this::mapToLeaveResponse
                        )
                        .toList();
    }

    public LeaveResponse getLeaveById(Long employeeId, Integer id) {
        Leave leave = leaveRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Leave request record not found ID: " + id)
                );
        if (!leave.getEmployeeId().equals(employeeId)) throw new ResourceNotFoundException("Employee ID did not match");
        return mapToLeaveResponse(leave);
    }

    public void update(Long eId, Integer lId, LeaveRequest leaveRequest){
        Leave leave = leaveRepository
                .findById(lId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Leave request record not found ID: " + lId)
                );
        if (!leave.getEmployeeId().equals(eId)) throw new ResourceNotFoundException("Employee ID did not match");
        leave.setStartDate(leaveRequest.getStartDate());
        leave.setEndDate(leaveRequest.getEndDate());
        leave.setLeaveType(leaveRequest.getLeaveType());
        leave.setReason(leaveRequest.getReason());
        leaveRepository.save(leave);
    }

    public void delete(Long eId, Integer lId){
        Leave leave = leaveRepository
                .findById(lId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Leave request record not found ID: " + lId)
                );
        if (!leave.getEmployeeId().equals(eId)) throw new ResourceNotFoundException("Employee ID did not match");
        leaveRepository.delete(leave);
    }

    public void acceptLeave(Long eId, Integer lId){
        Leave leave = leaveRepository
                .findById(lId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Leave request record not found ID: " + lId)
                );
        if (!leave.getEmployeeId().equals(eId)) throw new ResourceNotFoundException("Employee ID did not match");
        leave.setAccept(true);
        leaveRepository.save(leave);
    }

    public void refuseLeave(Long eId, Integer lId){
        Leave leave = leaveRepository
                .findById(lId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Leave request record not found ID: " + lId)
                );
        if (!leave.getEmployeeId().equals(eId)) throw new ResourceNotFoundException("Employee ID did not match");
        leave.setAccept(false);
        leaveRepository.save(leave);
    }

    public void activeLeave(Long eId, Integer lId){
        Leave leave = leaveRepository
                .findById(lId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Leave request record not found ID: " + lId)
                );
        if (!leave.getEmployeeId().equals(eId)) throw new ResourceNotFoundException("Employee ID did not match");
        leave.setActive(true);
        leaveRepository.save(leave);
    }

    public void inactiveLeave(Long eId, Integer lId){
        Leave leave = leaveRepository
                .findById(lId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Leave request record not found ID: " + lId)
                );
        if (!leave.getEmployeeId().equals(eId)) throw new ResourceNotFoundException("Employee ID did not match");
        leave.setActive(false);
        leaveRepository.save(leave);
    }

    private Leave mapToLeave(LeaveRequest leaveRequest) {
        return Leave
                .builder()
                .startDate(leaveRequest.getStartDate())
                .endDate(leaveRequest.getEndDate())
                .leaveType(leaveRequest.getLeaveType())
                .reason(leaveRequest.getReason())
                .accept(false)
                .active(true)
                .build();
    }

    private LeaveResponse mapToLeaveResponse(Leave leave) {
        return LeaveResponse
                .builder()
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .leaveType(leave.getLeaveType())
                .reason(leave.getReason())
                .accept(leave.isAccept())
                .active(leave.isActive())
                .build();
    }
}