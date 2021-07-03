package com.payroll.payrollWebService.service.EmpLeave;

import com.payroll.payrollWebService.models.payroll.trn_emp_leave;
import java.util.List;

public interface EmpLeaveService
{
    trn_emp_leave save(trn_emp_leave emp_leave);
    List<trn_emp_leave> findAll(Long empId);
}

