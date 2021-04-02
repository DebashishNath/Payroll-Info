package com.payroll.payrollWebService.service.EmpAttendance;

import com.payroll.payrollWebService.payload.response.MessageResponse;

public interface EmpAttendanceService {
    MessageResponse GenerateAllAttendance(Integer p_month, Integer p_year);
    MessageResponse GenerateSingleAttendance(Integer p_month, Integer p_year,Long p_emp_id);
}

