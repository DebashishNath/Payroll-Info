package com.payroll.payrollWebService.repository.payroll;

import com.payroll.payrollWebService.models.payroll.mst_employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<mst_employee, Long> {}
