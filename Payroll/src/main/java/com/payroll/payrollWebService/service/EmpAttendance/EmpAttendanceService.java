package com.payroll.payrollWebService.service.EmpAttendance;

import com.payroll.payrollWebService.models.payroll.AttendanceIdentity;
import com.payroll.payrollWebService.models.payroll.trn_emp_attendance;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;

public interface EmpAttendanceService {
    MessageResponse GenerateAllAttendance(Integer p_month, Integer p_year);
    List<trn_emp_attendance> GetAttendanceOfSingleEmployee(AttendanceIdentity attendanceIdentity);
}

