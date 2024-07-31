package com.hrm.attendancetracking.service;

import com.hrm.attendancetracking.client.ApiClient;
import com.hrm.attendancetracking.dto.response.AttendanceResponse;
import com.hrm.attendancetracking.dto.response.ListResponse;
import com.hrm.attendancetracking.exception.ResourceNotFoundException;
import com.hrm.attendancetracking.helper.DateAndTimeHelper;
import com.hrm.attendancetracking.model.AttendanceRecord;
import com.hrm.attendancetracking.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.stream.Collectors;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ATService {
    private final AttendanceRepository attendanceRepository;
    private final DateAndTimeHelper dateAndTimeHelper;
    private final ApiClient apiClient;

    public String attendanceEntry(Long employeeId) {
        log.info("Attendance Entry Service");
        Boolean employee = apiClient.existById(employeeId);
        if (!employee) throw new ResourceNotFoundException("Employee not found");
        AttendanceRecord attendanceRecord = AttendanceRecord
                .builder()
                .date(dateAndTimeHelper.getLocalDate())
                .clockIn(dateAndTimeHelper.getLocalTime())
                .employeeId(employeeId)
                .build();

        AttendanceRecord attendance = attendanceRepository.save(attendanceRecord);
        log.info("Attendance ID: {}", attendance.getAttendanceId());
        return format("Entry saved ID: %s", attendance.getAttendanceId());
    }

    public ListResponse getAllAttendance(Long empId) {
        return new ListResponse(attendanceRepository
                .findByEmployeeId(empId)
                .stream()
                .map(this::mapToAttendanceResponse)
                .collect(Collectors.toList()));
    }

    public AttendanceResponse getAttendanceById(Integer attendanceId) {
        AttendanceRecord attendanceRecord = attendanceRepository.findById(attendanceId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(format("Attendance Record ID: %s not found", attendanceId))
                );
        return mapToAttendanceResponse(attendanceRecord);
    }

    public void clockOut(Integer id) {
        AttendanceRecord attendanceRecord = attendanceRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(format("Attendance Record ID: %s not found", id))
                );
        attendanceRecord.setClockOut(dateAndTimeHelper.getLocalTime());
        LocalTime productiveTime = dateAndTimeHelper.productiveTime(attendanceRecord.getClockIn(),
                attendanceRecord.getClockOut());
        attendanceRecord.setProductive(productiveTime);
        attendanceRepository.save(attendanceRecord);
    }

    public void deleteAttendanceRecord(Integer attendanceId) {
        if (attendanceRepository.existsById(attendanceId)) attendanceRepository.deleteById(attendanceId);
        else throw new ResourceNotFoundException(format("Attendance Record ID: %s not found", attendanceId));
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