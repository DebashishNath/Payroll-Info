package com.payroll.payrollWebService.service.MonthlyEmpSalSummary;

import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalSummaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary_summary;

import java.util.Optional;

abstract class MonthlyEmpSalSummaryServiceImpl implements MonthlyEmpSalSummaryService{

    @Override
    public Optional<trn_monthly_emp_salary_summary> PrintSinglePaySlip(MonthlyEmpSalSummaryIdentity monthlyEmpSalarySummaryIdentity) {
        return new MonthlyEmpSalSummaryServiceDAL().PrintSinglePaySlip(monthlyEmpSalarySummaryIdentity);
    }
}
