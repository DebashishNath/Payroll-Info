package com.payroll.payrollWebService.service.EmpAttendance;

import com.payroll.payrollWebService.payload.response.MessageResponse;

abstract public class EmpAttendanceServiceImpl implements EmpAttendanceService{
    @Override
    public MessageResponse GenerateAttendance(Integer p_month, Integer p_year) {
       return new EmpAttendanceServiceDAL().GenerateAttendance(p_month,p_year);
    }
}
