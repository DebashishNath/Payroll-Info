package com.payroll.payrollWebService.service.EmpAttendance;

import com.payroll.payrollWebService.models.payroll.AttendanceIdentity;
import com.payroll.payrollWebService.models.payroll.trn_emp_attendance;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;

abstract public class EmpAttendanceServiceImpl implements EmpAttendanceService{
    @Override
    public MessageResponse ManageSingleEmpAttendance(trn_emp_attendance empAttendance){
        return new EmpAttendanceServiceDAL().ManageSingleEmpAttendance(empAttendance);
    }

    @Override
    public MessageResponse GenerateAllAttendance(Integer p_month, Integer p_year) {
       return new EmpAttendanceServiceDAL().GenerateAllAttendance(p_month,p_year);
    }

    @Override
    public List<trn_emp_attendance> GetAttendanceOfSingleEmployee(AttendanceIdentity attendanceIdentity){
        return new EmpAttendanceServiceDAL().GetAttendanceOfSingleEmployee(attendanceIdentity);
    }

}
