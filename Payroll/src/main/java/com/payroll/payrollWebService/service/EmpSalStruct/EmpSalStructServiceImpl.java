package com.payroll.payrollWebService.service.EmpSalStruct;

import com.payroll.payrollWebService.models.payroll.EmpEarnDedIdentity;
import com.payroll.payrollWebService.models.payroll.trn_emp_salary_structure;

import java.util.List;
import java.util.Optional;

abstract class EmpSalStructServiceImpl implements EmpSalStructService {

    @Override
    public trn_emp_salary_structure save(trn_emp_salary_structure empSalaryStruct) {
        return new EmpSalStructServiceDAL().save(empSalaryStruct);
    }
    @Override
    public trn_emp_salary_structure modify(trn_emp_salary_structure empSalaryStruct) {
        return new EmpSalStructServiceDAL().modify(empSalaryStruct);
    }
    @Override
    public Optional<trn_emp_salary_structure> findById(EmpEarnDedIdentity empEarnDedIdentity) {
        return new EmpSalStructServiceDAL().findById(empEarnDedIdentity);
    }
    @Override
    public List<trn_emp_salary_structure> findAll(Long empId){
        return new EmpSalStructServiceDAL().findAll(empId);
    }

    @Override
    public trn_emp_salary_structure updateEmpSalStruct(trn_emp_salary_structure empSalaryStructure){
        return new EmpSalStructServiceDAL().updateEmpSalStruct(empSalaryStructure);
    }
}