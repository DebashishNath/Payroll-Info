package com.payroll.payrollWebService.service.EmpLeave;

import com.payroll.payrollWebService.models.payroll.trn_emp_leave;

import java.util.List;
import java.util.Optional;


abstract class EmpLeaveServiceImpl implements EmpLeaveService
{
    @Override
    public trn_emp_leave save(trn_emp_leave emp_leave) {
        return new EmpLeaveServiceDAL().save(emp_leave);
    }

    @Override
    public List<trn_emp_leave> findAll(Long empId){
        return new EmpLeaveServiceDAL().findAll(empId);
    }

    @Override
    public Optional<trn_emp_leave> findById(Long id){ return new EmpLeaveServiceDAL().findById(id);}
}
