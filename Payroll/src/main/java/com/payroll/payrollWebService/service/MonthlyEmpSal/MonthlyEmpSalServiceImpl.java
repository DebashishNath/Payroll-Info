package com.payroll.payrollWebService.service.MonthlyEmpSal;

import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary_details;

import java.util.Optional;

abstract class MonthlyEmpSalServiceImpl implements MonthlyEmpSalService {

    @Override
    public trn_monthly_emp_salary_details save(trn_monthly_emp_salary_details monthlyEmpSalary) {
        return new MonthlyEmpSalServiceDAL().save(monthlyEmpSalary);
    }

    @Override
    public trn_monthly_emp_salary_details modify(trn_monthly_emp_salary_details monthlyEmpSalary) {
        return new MonthlyEmpSalServiceDAL().save(monthlyEmpSalary);
    }
    @Override
    public Optional<trn_monthly_emp_salary_details> findById(MonthlyEmpSalaryIdentity monthlyEmpSalaryIdentity) {
        return new MonthlyEmpSalServiceDAL().findById(monthlyEmpSalaryIdentity);
    }
}