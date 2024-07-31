package com.hrm.attendancetracking.service;

import com.hrm.attendancetracking.client.ApiClient;
import com.hrm.attendancetracking.dto.request.LeaveRequest;
import com.hrm.attendancetracking.dto.response.LeaveResponse;
import com.hrm.attendancetracking.dto.response.ListResponse;
import com.hrm.attendancetracking.exception.ResourceNotFoundException;
import com.hrm.attendancetracking.model.Leave;
import com.hrm.attendancetracking.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {
    private final LeaveRepository leaveRepository;
    private final ApiClient apiClient;

    public String createLeaveRequest(LeaveRequest leaveRequest) {
        Boolean employee = apiClient.existById(leaveRequest.getEmployeeId());
        if (!employee)
            throw new ResourceNotFoundException(format("Employee ID: %s not found", leaveRequest.getEmployeeId()));
        Leave leave = mapToLeave(leaveRequest);
        Leave leaveCreated = leaveRepository.save(leave);
        return format("Request submitted Leave ID: %s", leaveCreated.getId());
    }

    public ListResponse getLeaveRequests(Long id) {
        return new ListResponse(leaveRepository
                .findByEmployeeId(id)
                .stream().map(this::mapToLeaveResponse)
                .collect(Collectors.toList()));
    }

    public LeaveResponse getLeaveById(Integer id) {
        Leave leave = leaveRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(format("Leave ID: %s not found", id))
                );
        return mapToLeaveResponse(leave);
    }

    public void update(Integer id, LeaveRequest leaveRequest) {
        Leave leave = leaveRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(format("Leave ID: %s not found", id))
                );
        leave.setStartDate(leaveRequest.getStartDate());
        leave.setEndDate(leaveRequest.getEndDate());
        leave.setLeaveType(leaveRequest.getLeaveType());
        leave.setReason(leaveRequest.getReason());
        leaveRepository.save(leave);
    }

    public void delete(Integer id) {
        if (leaveRepository.existsById(id)) leaveRepository.deleteById(id);
        else throw new ResourceNotFoundException(format("Leave ID: %s not found", id));
    }

    public void acceptLeave(Integer id) {
        Leave leave = leaveRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(format("Leave ID: %s not found", id))
                );
        leave.setAccept(true);
        leaveRepository.save(leave);
    }

    public void refuseLeave(Integer id) {
        Leave leave = leaveRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(format("Leave ID: %s not found", id))
                );
        leave.setAccept(false);
        leaveRepository.save(leave);
    }

    public void activeLeave(Integer id) {
        Leave leave = leaveRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(format("Leave ID: %s not found", id))
                );
        leave.setActive(true);
        leaveRepository.save(leave);
    }

    public void inactiveLeave(Integer id) {
        Leave leave = leaveRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(format("Leave ID: %s not found", id))
                );
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
                .employeeId(leaveRequest.getEmployeeId())
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