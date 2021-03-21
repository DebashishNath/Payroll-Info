package com.payroll.payrollWebService.service.MonthlyEmpSal;

import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary;

import java.util.Optional;

abstract class MonthlyEmpSalServiceImpl implements MonthlyEmpSalService {

    @Override
    public trn_monthly_emp_salary save(trn_monthly_emp_salary monthlyEmpSalary) {
        return new MonthlyEmpSalServiceDAL().save(monthlyEmpSalary);
    }

    @Override
    public Optional<trn_monthly_emp_salary> findById(MonthlyEmpSalaryIdentity monthlyEmpSalaryIdentity) {
        return new MonthlyEmpSalServiceDAL().findById(monthlyEmpSalaryIdentity);
    }
}