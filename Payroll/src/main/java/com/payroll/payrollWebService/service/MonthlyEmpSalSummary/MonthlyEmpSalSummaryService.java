package com.payroll.payrollWebService.service.MonthlyEmpSalSummary;

import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalSummaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary_summary;

import java.util.Optional;

public interface MonthlyEmpSalSummaryService {
    Optional<trn_monthly_emp_salary_summary> PrintSinglePaySlip(MonthlyEmpSalSummaryIdentity empSalSummaryIdentity);
}
