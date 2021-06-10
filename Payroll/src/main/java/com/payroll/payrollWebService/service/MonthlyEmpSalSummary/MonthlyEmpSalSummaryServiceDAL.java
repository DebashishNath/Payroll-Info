package com.payroll.payrollWebService.service.MonthlyEmpSalSummary;

import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalSummaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary_summary;
import com.payroll.payrollWebService.repository.payroll.MonthlyEmpSalSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class MonthlyEmpSalSummaryServiceDAL extends MonthlyEmpSalSummaryServiceImpl {
    @Autowired
    private MonthlyEmpSalSummaryRepository monthlyEmpSalRep;

    @Override
    public Optional<trn_monthly_emp_salary_summary> PrintSinglePaySlip(MonthlyEmpSalSummaryIdentity monthlyEmpSalarySummaryIdentity) {
        System.out.println("Inside DAL");
        return monthlyEmpSalRep.findById(monthlyEmpSalarySummaryIdentity);
    }
}
