package com.hrm.attendancetracking.service;

import com.hrm.attendancetracking.dto.EmployeeClient;
import com.hrm.attendancetracking.dto.LeaveRequest;
import com.hrm.attendancetracking.dto.LeaveResponse;
import com.hrm.attendancetracking.exception.ResourceNotFoundException;
import com.hrm.attendancetracking.model.Leave;
import com.hrm.attendancetracking.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {
    private final LeaveRepository leaveRepository;
    private final RestTemplate restTemplate;

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

    public LeaveResponse getLeaveById(Integer id){
        Leave leave = leaveRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Leave request record not found ID: " + id)
                );

        EmployeeClient empObj = restTemplate
                .getForObject(
                        "http://EMPLOYEE-SERVICE/api/v1/employees/" + leave.getEmployeeId(),
                        EmployeeClient.class
                );

        LeaveResponse leaveResponse = mapToLeaveResponse(leave);
        leaveResponse.setEmployee(empObj);

        return leaveResponse;
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

    private LeaveResponse mapToLeaveResponse(Leave leave){
        return LeaveResponse
                .builder()
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .leaveType(leave.getLeaveType())
                .status(leave.getStatus())
                .reason(leave.getReason())
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