package com.payroll.payrollWebService.service.MonthlyEmpSalSummary;

import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalSummaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary_summary;
import com.payroll.payrollWebService.models.payroll.PrintPaySlip;

import java.util.Optional;

abstract class MonthlyEmpSalSummaryServiceImpl implements MonthlyEmpSalSummaryService{

    @Override
    public PrintPaySlip PrintSinglePaySlip(MonthlyEmpSalSummaryIdentity monthlyEmpSalarySummaryIdentity) {
        return new MonthlyEmpSalSummaryServiceDAL().PrintSinglePaySlip(monthlyEmpSalarySummaryIdentity);
    }
}
