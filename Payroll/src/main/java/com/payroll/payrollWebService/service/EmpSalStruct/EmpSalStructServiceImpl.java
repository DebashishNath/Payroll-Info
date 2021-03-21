package com.payroll.payrollWebService.service.EmpSalStruct;

import com.payroll.payrollWebService.models.payroll.EmpEarnDedIdentity;
import com.payroll.payrollWebService.models.payroll.trn_emp_salary_structure;

import java.util.Optional;

abstract class EmpSalStructServiceImpl implements EmpSalStructService {

    @Override
    public trn_emp_salary_structure save(trn_emp_salary_structure empSalaryStruct) {
        return new EmpSalStructServiceDAL().save(empSalaryStruct);
    }

    @Override
    public Optional<trn_emp_salary_structure> findById(EmpEarnDedIdentity empEarnDedIdentity) {
        return new EmpSalStructServiceDAL().findById(empEarnDedIdentity);
    }
}