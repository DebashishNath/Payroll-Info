package com.payroll.payrollWebService.service.EmpLeaveEntitle;

import com.payroll.payrollWebService.models.payroll.trn_emp_leave_entitlement;

import java.util.List;
import java.util.Optional;

public interface EmpLeaveEntitleService {
    trn_emp_leave_entitlement save(trn_emp_leave_entitlement emp_leave);
    List<trn_emp_leave_entitlement> findAll(Long empId,Long finYearId);
    Optional<trn_emp_leave_entitlement> findById(Long id);
}

