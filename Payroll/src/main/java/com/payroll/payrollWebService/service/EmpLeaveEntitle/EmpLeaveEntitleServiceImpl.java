package com.payroll.payrollWebService.service.EmpLeaveEntitle;

import com.payroll.payrollWebService.models.payroll.trn_emp_leave_entitlement;
import com.payroll.payrollWebService.service.EmpLeaveEntitle.EmpLeaveEntitleService;
import com.payroll.payrollWebService.service.EmpLeave.EmpLeaveServiceDAL;

import java.util.List;
import java.util.Optional;

abstract class EmpLeaveEntitleServiceImpl implements EmpLeaveEntitleService
{
    @Override
    public trn_emp_leave_entitlement save(trn_emp_leave_entitlement empLeaveEntitlement) {
        return new EmpLeaveEntitlementServiceDAL().save(empLeaveEntitlement);
    }

    @Override
    public List<trn_emp_leave_entitlement> findAll(Long empId,Long finYearId){
        return new EmpLeaveEntitlementServiceDAL().findAll(empId,finYearId);
    }

    @Override
    public Optional<trn_emp_leave_entitlement> findById(Long id){ return new EmpLeaveEntitlementServiceDAL().findById(id);}
}