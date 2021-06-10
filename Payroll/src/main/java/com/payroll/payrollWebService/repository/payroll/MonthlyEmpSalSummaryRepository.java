package com.payroll.payrollWebService.repository.payroll;

import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalSummaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary_summary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyEmpSalSummaryRepository extends CrudRepository
        <trn_monthly_emp_salary_summary, MonthlyEmpSalSummaryIdentity> {
}
