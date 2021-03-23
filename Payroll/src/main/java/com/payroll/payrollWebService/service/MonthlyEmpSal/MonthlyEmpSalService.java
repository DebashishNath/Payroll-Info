package com.payroll.payrollWebService.service.MonthlyEmpSal;

import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary;

import java.util.Optional;

public interface MonthlyEmpSalService {
    trn_monthly_emp_salary save(trn_monthly_emp_salary monthlyEmpSalary);
    trn_monthly_emp_salary modify(trn_monthly_emp_salary monthlyEmpSalary);
    Optional<trn_monthly_emp_salary> findById(MonthlyEmpSalaryIdentity monthlyEmpSalaryIdentity);
}
