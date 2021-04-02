package com.payroll.payrollWebService.service.EmpAttendance;

import com.payroll.payrollWebService.payload.response.MessageResponse;

abstract public class EmpAttendanceServiceImpl implements EmpAttendanceService{
    @Override
    public MessageResponse GenerateAllAttendance(Integer p_month, Integer p_year) {
       return new EmpAttendanceServiceDAL().GenerateAllAttendance(p_month,p_year);
    }

    @Override
    public MessageResponse GenerateSingleAttendance(Integer p_month, Integer p_year,Long p_emp_id){
        return new EmpAttendanceServiceDAL().GenerateSingleAttendance(p_month,p_year,p_emp_id);
    }
}
