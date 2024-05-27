package com.hrm.attendancetracking.service;

import com.hrm.attendancetracking.dto.LeaveRequest;
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

    public String createLeaveRequest(LeaveRequest leaveRequest, Long employeeId) {
        Leave leave = mapToLeave(leaveRequest);
        leave.setEmployeeId(employeeId);
        Leave leaveCreated = leaveRepository.save(leave);
        return "Leave request submitted ID: " + leaveCreated.getLeaveRequestId();
    }

    public List<LeaveRequest> getLeaveRequests() {
        return
                leaveRepository
                        .findAll()
                        .stream().map(
                                this::mapToLeaveRequest
                        )
                        .toList();
    }

    public LeaveRequest getLeaveById(Integer id){
        Leave leave = leaveRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Leave request record not found ID: " + id)
                );

        return mapToLeaveRequest(leave);
    }

    private Leave mapToLeave(LeaveRequest leaveRequest){
        return Leave
                .builder()
                .startDate(leaveRequest.getStartDate())
                .endDate(leaveRequest.getEndDate())
                .leaveType(leaveRequest.getLeaveType())
                .status(leaveRequest.getStatus())
                .reason(leaveRequest.getReason())
                .build();
    }

    private LeaveRequest mapToLeaveRequest(Leave leave){
        return LeaveRequest
                .builder()
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .leaveType(leave.getLeaveType())
                .status(leave.getStatus())
                .reason(leave.getReason())
                .build();
    }
}
