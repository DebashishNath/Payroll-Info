package com.payroll.payrollWebService.service.MonthlyEmpSal;

import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary_details;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.Date;
import java.util.Optional;

public interface MonthlyEmpSalService {
    trn_monthly_emp_salary_details save(trn_monthly_emp_salary_details monthlyEmpSalary);
    trn_monthly_emp_salary_details modify(trn_monthly_emp_salary_details monthlyEmpSalary);
    Optional<trn_monthly_emp_salary_details> findById(MonthlyEmpSalaryIdentity monthlyEmpSalaryIdentity);
    MessageResponse GeneratePaySlip(Integer month, Integer year, Date salary_date);
}
