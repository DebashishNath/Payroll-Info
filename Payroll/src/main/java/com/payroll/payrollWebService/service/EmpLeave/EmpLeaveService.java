package com.payroll.payrollWebService.service.EmpLeave;

import com.payroll.payrollWebService.models.payroll.trn_emp_leave;
import java.util.List;
import java.util.Optional;

public interface EmpLeaveService
{
    trn_emp_leave save(trn_emp_leave emp_leave);
    List<trn_emp_leave> findAll(Long empId);
    Optional<trn_emp_leave> findById(Long id);
}

