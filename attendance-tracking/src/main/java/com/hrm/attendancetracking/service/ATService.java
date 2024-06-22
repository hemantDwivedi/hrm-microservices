package com.hrm.attendancetracking.service;

import com.hrm.attendancetracking.dto.AttendanceResponse;
import com.hrm.attendancetracking.dto.EmployeeClient;
import com.hrm.attendancetracking.exception.ResourceNotFoundException;
import com.hrm.attendancetracking.model.AttendanceRecord;
import com.hrm.attendancetracking.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ATService {
    private final AttendanceRepository attendanceRepository;
    private final RestTemplate restTemplate;

    public String attendanceEntry(Long employeeId) {
        AttendanceRecord attendanceRecord = AttendanceRecord
                .builder()
                .date(getDateString())
                .inTime(getTimeString())
                .employeeId(employeeId)
                .build();

        AttendanceRecord attendance = attendanceRepository.save(attendanceRecord);
        return "Attendance recorded ID: " + attendance.getAttendanceId();
    }

    public List<AttendanceRecord> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public AttendanceResponse getAttendanceById(Integer attendanceId){
        AttendanceRecord attendanceRecord = attendanceRepository
                .findById(attendanceId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Attendance Record not found ID: " + attendanceId)
                );

        // call Employee Service, and get an employee by id
        EmployeeClient empObj = restTemplate
                .getForObject(
                        "http://EMPLOYEE-SERVICE/api/v1/employees/" + attendanceRecord.getEmployeeId(),
                        EmployeeClient.class
                );
        log.info(String.valueOf(empObj));

        AttendanceResponse attendanceResponse = mapToAttendanceResponse(attendanceRecord);
        attendanceResponse.setEmployee(empObj);
        return attendanceResponse;
    }

    public String recordOutTime(Integer id) throws ParseException {
        AttendanceRecord attendanceRecord = attendanceRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Record not found")
                );

        attendanceRecord.setOutTime(getTimeString());
        String workingTime = calTotalHours(attendanceRecord);
        attendanceRecord.setTotalWorkingTime(workingTime);
        attendanceRepository.save(attendanceRecord);
        return "Entry saved!";
    }

    public String deleteAttendanceRecord(Integer attendanceId){
        attendanceRepository.deleteById(attendanceId);
        return "Deleted!";
    }

    private String getDateString() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    private String getTimeString() {
        return new SimpleDateFormat("HH:mm").format(new Date());
    }

    private String calTotalHours(AttendanceRecord attendance) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date d1 = format.parse(attendance.getInTime());
        Date d2 = format.parse(attendance.getOutTime());

        long timeDiff = Objects.requireNonNull(d2).getTime() - Objects.requireNonNull(d1).getTime();

        long hh = timeDiff / (60 * 60 * 1000);
        long mm = timeDiff / (60 * 1000) % 60;

        return hh + "H:" + mm + "M";
    }

    private AttendanceResponse mapToAttendanceResponse(AttendanceRecord attendanceRecord){
        return AttendanceResponse.builder()
                .date(attendanceRecord.getDate())
                .inTime(attendanceRecord.getInTime())
                .outTime(attendanceRecord.getOutTime())
                .totalWorkingTime(attendanceRecord.getTotalWorkingTime())
                .build();
    }

}