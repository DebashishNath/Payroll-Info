package com.payroll.payrollWebService.service.MonthlyEmpSalSummary;

import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalSummaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary_summary;
import com.payroll.payrollWebService.models.payroll.PrintPaySlip;

import java.util.Optional;

public interface MonthlyEmpSalSummaryService {
    PrintPaySlip PrintSinglePaySlip(MonthlyEmpSalSummaryIdentity empSalSummaryIdentity);
}
