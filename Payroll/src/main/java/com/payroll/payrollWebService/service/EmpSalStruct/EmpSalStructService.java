package com.payroll.payrollWebService.service.EmpSalStruct;

import com.payroll.payrollWebService.models.payroll.trn_emp_salary_structure;
import com.payroll.payrollWebService.models.payroll.EmpEarnDedIdentity;

import java.util.Optional;

public interface EmpSalStructService {
    trn_emp_salary_structure save(trn_emp_salary_structure empSalaryStructure);
    trn_emp_salary_structure modify(trn_emp_salary_structure empSalaryStructure);
    Optional<trn_emp_salary_structure> findById(EmpEarnDedIdentity empEarnDedIdentity);

}
