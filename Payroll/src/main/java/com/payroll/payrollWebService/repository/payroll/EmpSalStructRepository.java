package com.payroll.payrollWebService.repository.payroll;

import com.payroll.payrollWebService.models.payroll.EmpEarnDedIdentity;
import com.payroll.payrollWebService.models.payroll.trn_emp_salary_structure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpSalStructRepository extends CrudRepository<trn_emp_salary_structure, EmpEarnDedIdentity> {
}

