package com.payroll.payrollWebService.repository.payroll;

import org.springframework.data.repository.CrudRepository;
import com.payroll.payrollWebService.models.payroll.trn_emp_leave;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpLeaveRepository extends CrudRepository
        <trn_emp_leave, Long> {
}
