package com.payroll.payrollWebService.repository.payroll;

import com.payroll.payrollWebService.models.payroll.trn_emp_leave_entitlement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpLeaveEntitlementRepository extends CrudRepository<trn_emp_leave_entitlement, Long> {
}