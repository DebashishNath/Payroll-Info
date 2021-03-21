package com.payroll.payrollWebService.repository.payroll;

import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyEmpSalaryRepository extends CrudRepository<trn_monthly_emp_salary, MonthlyEmpSalaryIdentity> {
}
