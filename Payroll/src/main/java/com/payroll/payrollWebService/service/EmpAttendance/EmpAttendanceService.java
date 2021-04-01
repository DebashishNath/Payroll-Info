package com.payroll.payrollWebService.service.EmpAttendance;

import com.payroll.payrollWebService.payload.response.MessageResponse;

public interface EmpAttendanceService {
    MessageResponse GenerateAttendance(Integer p_month, Integer p_year);
}

