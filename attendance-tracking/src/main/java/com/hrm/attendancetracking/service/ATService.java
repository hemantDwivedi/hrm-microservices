package com.hrm.attendancetracking.service;

import com.hrm.attendancetracking.client.ApiClient;
import com.hrm.attendancetracking.dto.AttendanceResponse;
import com.hrm.attendancetracking.exception.ResourceNotFoundException;
import com.hrm.attendancetracking.helper.DateAndTimeHelper;
import com.hrm.attendancetracking.model.AttendanceRecord;
import com.hrm.attendancetracking.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ATService {
    private final AttendanceRepository attendanceRepository;
    private final DateAndTimeHelper dateAndTimeHelper;
    private final ApiClient apiClient;

    public String attendanceEntry(Long employeeId) {
        Boolean employee = apiClient.existById(employeeId);
        if (!employee) throw new ResourceNotFoundException("Employee not found");
        AttendanceRecord attendanceRecord = AttendanceRecord
                .builder()
                .date(dateAndTimeHelper.getLocalDate())
                .clockIn(dateAndTimeHelper.getLocalTime())
                .employeeId(employeeId)
                .build();

        AttendanceRecord attendance = attendanceRepository.save(attendanceRecord);
        return "Attendance recorded ID: " + attendance.getAttendanceId();
    }

    public List<AttendanceResponse> getAllAttendance(Long empId) {
        return attendanceRepository
                .findByEmployeeId(empId)
                .stream()
                .map(this::mapToAttendanceResponse)
                .collect(Collectors.toList());
    }

    public AttendanceResponse getAttendanceById(Long eId, Integer attendanceId) {
        AttendanceRecord attendanceRecord = attendanceRepository.findById(attendanceId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Record not found")
                );
        if (!attendanceRecord.getEmployeeId().equals(eId))
            throw new ResourceNotFoundException("Employee ID did not match!");
        return mapToAttendanceResponse(attendanceRecord);
    }

    public void clockOut(Long eId, Integer id) {
        AttendanceRecord attendanceRecord = attendanceRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Record not found")
                );
        if (!attendanceRecord.getEmployeeId().equals(eId))
            throw new ResourceNotFoundException("Employee ID did not match!");
        attendanceRecord.setClockOut(dateAndTimeHelper.getLocalTime());
        LocalTime productiveTime = dateAndTimeHelper.productiveTime(attendanceRecord.getClockIn(),
                attendanceRecord.getClockOut());
        attendanceRecord.setProductive(productiveTime);
        attendanceRepository.save(attendanceRecord);
    }

    public void deleteAttendanceRecord(Long eId, Integer attendanceId) {
        AttendanceRecord attendanceRecord = attendanceRepository.findById(attendanceId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Record not found")
                );
        if (!attendanceRecord.getEmployeeId().equals(eId))
            throw new ResourceNotFoundException("Employee ID did not match!");
        attendanceRepository.delete(attendanceRecord);
    }

    private AttendanceResponse mapToAttendanceResponse(AttendanceRecord attendanceRecord) {
        return AttendanceResponse.builder()
                .id(attendanceRecord.getAttendanceId())
                .date(attendanceRecord.getDate())
                .clockIn(attendanceRecord.getClockIn())
                .clockOut(attendanceRecord.getClockOut())
                .productive(attendanceRecord.getProductive())
                .build();
    }

}